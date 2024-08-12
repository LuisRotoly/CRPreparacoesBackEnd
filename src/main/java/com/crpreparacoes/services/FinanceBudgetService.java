package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.financeBudget.CreateFinanceBudgetRequest;
import com.crpreparacoes.dto.FinanceBudgetDTO;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.Budget;
import com.crpreparacoes.models.FinanceBudget;
import com.crpreparacoes.models.LaborOrBikePartBudget;
import com.crpreparacoes.models.Status;
import com.crpreparacoes.repositories.BudgetRepository;
import com.crpreparacoes.repositories.FinanceBudgetRepository;
import com.crpreparacoes.repositories.LaborOrBikePartBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceBudgetService {
    
    @Autowired
    private FinanceBudgetRepository financeBudgetRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private LaborOrBikePartBudgetRepository laborOrBikePartBudgetRepository;

    public List<FinanceBudgetDTO> listAllFinanceBudgets() {
        List<Budget> budgetList = budgetRepository.listAllBudgetsFinishedByaMonth(Status.StatusEnum.FINISHED.id, LocalDateTime.now().minusMonths(1));
        List<FinanceBudgetDTO> financeBudgetDTOList = new ArrayList<>();
        for (Budget budget:budgetList) {
            FinanceBudgetDTO financeBudgetDTO = new FinanceBudgetDTO();
            financeBudgetDTO.setBudgetId(budget.getId());
            financeBudgetDTO.setPlate(budget.getPlate());
            financeBudgetDTO.setClientName(budget.getClient().getName());
            financeBudgetDTO.setFinalizedAt(budget.getUpdatedAt());
            financeBudgetDTO.setBikeNameAndBrand(budget.getBikeName()+" "+budget.getBikeBrand());
            double totalValue = getTotalvalue(budget.getId(), budget.getDiscountPercentage());
            financeBudgetDTO.setTotalValue(totalValue);
            Double paidValue = financeBudgetRepository.getSumPaidValue(budget.getId());
            if(budget.isPaid()){
                financeBudgetDTO.setToBePaid(0);
            }else if(paidValue != null) {
                financeBudgetDTO.setToBePaid(totalValue-paidValue);
            }else{
                financeBudgetDTO.setToBePaid(totalValue);
            }
            financeBudgetDTOList.add(financeBudgetDTO);
        }
        return financeBudgetDTOList;
    }

    private double getTotalvalue(Long budgetId, Integer discount){
        double totalValue = 0;
        List<LaborOrBikePartBudget> laborOrBikePartBudgetList = laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(budgetId);
        for (LaborOrBikePartBudget laborOrBikePartBudget:laborOrBikePartBudgetList) {
            totalValue = totalValue+laborOrBikePartBudget.getValue()*laborOrBikePartBudget.getQuantity();
        }
        if(discount != null){
            totalValue = totalValue - (totalValue*discount/100);
        }
        return totalValue;
    }

    public List<FinanceBudgetDTO> filterFinanceBudgetListRequest(String word, boolean isInDebit, boolean isPaid) {
        List<Budget> budgetList = budgetRepository.filterListFinanceBudgets(word, Status.StatusEnum.FINISHED.id);
        List<FinanceBudgetDTO> financeBudgetDTOList = new ArrayList<>();
        for (Budget budget:budgetList) {
            FinanceBudgetDTO financeBudgetDTO = new FinanceBudgetDTO();
            financeBudgetDTO.setBudgetId(budget.getId());
            financeBudgetDTO.setPlate(budget.getPlate());
            financeBudgetDTO.setClientName(budget.getClient().getName());
            financeBudgetDTO.setFinalizedAt(budget.getUpdatedAt());
            financeBudgetDTO.setBikeNameAndBrand(budget.getBikeName()+" "+budget.getBikeBrand());
            double totalValue = getTotalvalue(budget.getId(), budget.getDiscountPercentage());
            financeBudgetDTO.setTotalValue(totalValue);
            Double paidValue = financeBudgetRepository.getSumPaidValue(budget.getId());
            if(budget.isPaid()){
                financeBudgetDTO.setToBePaid(0);
            }else if(paidValue != null) {
                financeBudgetDTO.setToBePaid(totalValue-paidValue);
            }else{
                financeBudgetDTO.setToBePaid(totalValue);
            }
            if(isInDebit && financeBudgetDTO.getToBePaid()>0 && !budget.isPaid()) {
                financeBudgetDTOList.add(financeBudgetDTO);
            }else if(isPaid && financeBudgetDTO.getToBePaid()<=0 && budget.isPaid()){
                financeBudgetDTOList.add(financeBudgetDTO);
            }else if(!isInDebit && !isPaid){
                financeBudgetDTOList.add(financeBudgetDTO);
            }
        }
        return financeBudgetDTOList;
    }

    public FinanceBudgetDTO listFinanceBudgetById(Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId).get();
        FinanceBudgetDTO financeBudgetDTO = new FinanceBudgetDTO();
        financeBudgetDTO.setBudgetId(budgetId);
        financeBudgetDTO.setPlate(budget.getPlate());
        financeBudgetDTO.setClientName(budget.getClient().getName());
        financeBudgetDTO.setFinalizedAt(budget.getUpdatedAt());
        financeBudgetDTO.setBikeNameAndBrand(budget.getBikeName()+" "+budget.getBikeBrand());
        financeBudgetDTO.setNotes(budget.getNotes());
        financeBudgetDTO.setPaid(budget.isPaid());
        double totalValue = getTotalvalue(budget.getId(), budget.getDiscountPercentage());
        financeBudgetDTO.setTotalValue(totalValue);
        Double paidValue = financeBudgetRepository.getSumPaidValue(budget.getId());
        if(paidValue != null) {
            financeBudgetDTO.setToBePaid(totalValue-paidValue);
        }else{
            financeBudgetDTO.setToBePaid(totalValue);
        }
        financeBudgetDTO.setFinanceBudgetList(financeBudgetRepository.findAllFinanceBudgetByBudgetId(budgetId));
        return financeBudgetDTO;
    }

    public List<FinanceBudget> getPaymentsListById(Long id) {
        return financeBudgetRepository.findAllFinanceBudgetByBudgetId(id);
    }

    public void removePaymentById(Long id) {
        try {
            financeBudgetRepository.deleteById(id);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar remover pagamento!");
        }
    }

    public void addPayment(CreateFinanceBudgetRequest createFinanceBudgetRequest) {
        FinanceBudget financeBudget = new FinanceBudget();
        financeBudget.setBudget(budgetRepository.findById(createFinanceBudgetRequest.getBudgetId()).get());
        financeBudget.setPaymentFormat(createFinanceBudgetRequest.getPaymentFormat());
        financeBudget.setValue(createFinanceBudgetRequest.getPaymentValue());
        financeBudget.setPaidAt(LocalDateTime.now());
        financeBudget.setNotes(createFinanceBudgetRequest.getNotes());
        try {
            financeBudgetRepository.save(financeBudget);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar pagamento!");
        }
    }

    public double getToBePaidById(Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId).get();
        double totalValue = getTotalvalue(budget.getId(), budget.getDiscountPercentage());
        Double paidValue = financeBudgetRepository.getSumPaidValue(budget.getId());
        if(paidValue != null) {
            return totalValue-paidValue;
        }else{
            return totalValue;
        }
    }

    public double getTotalToReceive() {
        List<Budget> budgetList = budgetRepository.listAllBudgetsFinished(Status.StatusEnum.FINISHED.id);
        double sumValue = 0;
        for (Budget budget:budgetList) {
            double totalBudgetValue = getTotalvalue(budget.getId(), budget.getDiscountPercentage());
            Double paidValue = financeBudgetRepository.getSumPaidValue(budget.getId());
            if(paidValue != null) {
                sumValue = sumValue + (totalBudgetValue - paidValue);
            }else{
                sumValue = sumValue + totalBudgetValue;
            }
        }
        return sumValue;
    }
}
