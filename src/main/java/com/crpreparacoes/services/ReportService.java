package com.crpreparacoes.services;

import com.crpreparacoes.dto.ReportDTO;
import com.crpreparacoes.models.BikePart;
import com.crpreparacoes.models.Budget;
import com.crpreparacoes.models.LaborOrBikePartBudget;
import com.crpreparacoes.models.Status;
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

    public List<Double> getGrossIncomeData(String year) {
        List<Double> receivedMoneyList = new ArrayList<>();
        List<ReportDTO> reportDTOBudgetList = financeBudgetRepository.getMonthAndValueSumFromBudget(year);
        List<ReportDTO> reportDTOSingleSaleList = singleSaleFinanceRepository.getMonthAndValueSumFromSingleSale(year);
        int budgetCount = 0;
        int singleSaleCount = 0;
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
        }
        return receivedMoneyList;
    }

    public List<Double> getNetRevenueData(String year) {
        List<Double> netRevenueList = new ArrayList<>();
        List<Double> receivedMoneyList = getGrossIncomeData(year);
        List<Double> spentMoneyList = getBikePartSpentData(year);
        for (int i = 0; i < 12; i++) {
            netRevenueList.add(receivedMoneyList.get(i)-spentMoneyList.get(i));
        }
        return netRevenueList;
    }

    public List<Double> getBikePartSpentData(String year) {
        List<Double> spentMoneyList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            spentMoneyList.add((double) 0);
        }
        List<Budget> budgetList = budgetRepository.listAllBudgetsFinishedAtYear(Status.StatusEnum.FINISHED.id, year);
        for (Budget budget : budgetList) {
            List<LaborOrBikePartBudget> laborOrBikePartBudgetList = laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(budget.getId());
            double bikePartSpent = 0;
            for (LaborOrBikePartBudget laborOrBikePartBudget : laborOrBikePartBudgetList) {
                BikePart bikePart = bikePartRepository.findByName(laborOrBikePartBudget.getName());
                if(bikePart != null) {
                    bikePartSpent = bikePartSpent + (bikePart.getValue() * laborOrBikePartBudget.getQuantity());
                }
            }
            for (int monthCount = 0; monthCount < monthList.length; monthCount++) {
                if((monthCount+1) == budget.getUpdatedAt().getMonth().getValue()){
                    spentMoneyList.set(monthCount, (spentMoneyList.get(monthCount) + bikePartSpent));
                    break;
                }
            }
        }
        return spentMoneyList;
    }
}
