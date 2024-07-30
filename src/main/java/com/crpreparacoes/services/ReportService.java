package com.crpreparacoes.services;

import com.crpreparacoes.dto.ReportDTO;
import com.crpreparacoes.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private static final String[] monthList = {"January","February","March","April","May","June","July","August","September","October","November","December"};

    @Autowired
    private FinanceBudgetRepository financeBudgetRepository;

    @Autowired
    private SingleSaleFinanceRepository singleSaleFinanceRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private LaborOrBikePartBudgetRepository laborOrBikePartBudgetRepository;

    @Autowired
    private BikePartRepository bikePartRepository;

    @Autowired
    private DebitPaymentRepository debitPaymentRepository;

    public List<Double> getGrossIncomeData(String year) {
        List<Double> receivedMoneyList = new ArrayList<>();
        List<ReportDTO> reportDTOBudgetList = financeBudgetRepository.getMonthAndValueSumFromBudget(year);
        List<ReportDTO> reportDTOSingleSaleList = singleSaleFinanceRepository.getMonthAndValueSumFromSingleSale(year);
        List<ReportDTO> reportDTODebitPaymentList = debitPaymentRepository.getMonthAndValueSumFromDebitPayment(year);
        int budgetCount = 0;
        int singleSaleCount = 0;
        int debitPaymentCount = 0;
        for (String month: monthList) {
            if(budgetCount < reportDTOBudgetList.size()) {
                if (month.equals(reportDTOBudgetList.get(budgetCount).getmonth())) {
                    receivedMoneyList.add(reportDTOBudgetList.get(budgetCount).gettotalValue());
                    budgetCount++;
                } else {
                    receivedMoneyList.add((double) 0);
                }
            } else {
                receivedMoneyList.add((double) 0);
            }
            if(singleSaleCount < reportDTOSingleSaleList.size()){
                if (month.equals(reportDTOSingleSaleList.get(singleSaleCount).getmonth())) {
                    receivedMoneyList.set(receivedMoneyList.size()-1, reportDTOSingleSaleList.get(singleSaleCount).gettotalValue() + receivedMoneyList.get(receivedMoneyList.size()-1));
                    singleSaleCount++;
                }
            }
            if(debitPaymentCount < reportDTODebitPaymentList.size()){
                if (month.equals(reportDTODebitPaymentList.get(debitPaymentCount).getmonth())) {
                    receivedMoneyList.set(receivedMoneyList.size()-1, reportDTODebitPaymentList.get(debitPaymentCount).gettotalValue() + receivedMoneyList.get(receivedMoneyList.size()-1));
                    debitPaymentCount++;
                }
            }
        }
        return receivedMoneyList;
    }

    public List<Double> getNetRevenueData(String year) {
        List<Double> netRevenueList = new ArrayList<>();
        List<Double> receivedMoneyList = getGrossIncomeData(year);
        List<Double> spentMoneyList = getBikePartSpentData(year);
        for (int i = 0; i < 12; i++) {
            netRevenueList.add(receivedMoneyList.get(i)+spentMoneyList.get(i));
        }
        return netRevenueList;
    }

    public List<Double> getBikePartSpentData(String year) {
        List<Double> debitMoneyList = new ArrayList<>();
        List<ReportDTO> reportDTODebitPaymentList = debitPaymentRepository.getMonthAndNegativeValueSumFromDebitPayment(year);
        int debitPaymentCount = 0;
        for (String month: monthList) {
            if(debitPaymentCount < reportDTODebitPaymentList.size()){
                if (month.equals(reportDTODebitPaymentList.get(debitPaymentCount).getmonth())) {
                    debitMoneyList.add(reportDTODebitPaymentList.get(debitPaymentCount).gettotalValue());
                    debitPaymentCount++;
                }else {
                    debitMoneyList.add((double) 0);
                }
            }else{
                debitMoneyList.add((double) 0);
            }
        }
        return debitMoneyList;
    }
}
