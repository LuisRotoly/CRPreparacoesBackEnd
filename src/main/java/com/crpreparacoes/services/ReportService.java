package com.crpreparacoes.services;

import com.crpreparacoes.dto.ReportDTO;
import com.crpreparacoes.repositories.FinanceBudgetRepository;
import com.crpreparacoes.repositories.SingleSaleFinanceRepository;
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

    public List<Double> getReportData(String year) {
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
}
