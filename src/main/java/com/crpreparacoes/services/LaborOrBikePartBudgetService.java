package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.budget.AddLaborOrBikePartBudget;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.Budget;
import com.crpreparacoes.models.LaborOrBikePartBudget;
import com.crpreparacoes.repositories.BudgetRepository;
import com.crpreparacoes.repositories.LaborOrBikePartBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LaborOrBikePartBudgetService {

    @Autowired
    private LaborOrBikePartBudgetRepository laborOrBikePartBudgetRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    public List<LaborOrBikePartBudget> listLaborOrBikePartBudgetById(Long budgetId) {
        return laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(budgetId);
    }

    public double addLaborOrBikePartBudget(AddLaborOrBikePartBudget addLaborOrBikePartBudget) {
        Budget budget = budgetRepository.findById(addLaborOrBikePartBudget.getLaborOrBikePartBudget().getBudget().getId()).get();
        double totalValue = budget.getTotalValue() + (addLaborOrBikePartBudget.getLaborOrBikePartBudget().getQuantity()*addLaborOrBikePartBudget.getLaborOrBikePartBudget().getValue());
        budget.setTotalValue(totalValue);
        budget.setUpdatedAt(LocalDateTime.now());
        try {
            laborOrBikePartBudgetRepository.save(addLaborOrBikePartBudget.getLaborOrBikePartBudget());
            budgetRepository.save(budget);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o serviço!");
        }
        return totalValue;
    }

    public double removeLaborOrBikePartBudget(Long id) {
        LaborOrBikePartBudget laborOrBikePartBudget = laborOrBikePartBudgetRepository.findById(id).get();
        Budget budget = budgetRepository.findById(laborOrBikePartBudget.getBudget().getId()).get();
        double totalValue = budget.getTotalValue() - (laborOrBikePartBudget.getQuantity()*laborOrBikePartBudget.getValue());
        budget.setTotalValue(totalValue);
        budget.setUpdatedAt(LocalDateTime.now());
        try {
            laborOrBikePartBudgetRepository.delete(laborOrBikePartBudget);
            budgetRepository.save(budget);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar remover o serviço!");
        }
        return totalValue;
    }
}
