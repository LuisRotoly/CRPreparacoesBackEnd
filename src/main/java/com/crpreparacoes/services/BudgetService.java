package com.crpreparacoes.services;

import com.crpreparacoes.dto.BudgetDTO;
import com.crpreparacoes.bodyrequestinput.budget.CreateBudgetRequest;
import com.crpreparacoes.bodyrequestinput.budget.EditBudgetRequest;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.*;
import com.crpreparacoes.repositories.BudgetRepository;
import com.crpreparacoes.repositories.ClientRepository;
import com.crpreparacoes.repositories.LaborOrBikePartBudgetRepository;
import com.crpreparacoes.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private LaborOrBikePartBudgetRepository laborOrBikePartBudgetRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StatusRepository statusRepository;

    public List<Budget> listAllBudgets() {
        return budgetRepository.listAllBudgets();
    }

    public List<Budget> filterListBudgets(String word) {
        return budgetRepository.filterListBudgets(word);
    }

    public BudgetDTO listBudgetById(Long id) {
        Budget budget = budgetRepository.findById(id).get();
        List<LaborOrBikePartBudget> laborOrBikePartBudgetList = laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(id);
        BudgetDTO budgetDTO = new BudgetDTO();
        budgetDTO.setClient(budget.getClient());
        budgetDTO.setBikeName(budget.getBikeName());
        budgetDTO.setBikeBrand(budget.getBikeBrand());
        budgetDTO.setEngineCapacity(budget.getEngineCapacity());
        budgetDTO.setYear(budget.getYear());
        budgetDTO.setPlate(budget.getPlate());
        budgetDTO.setLaborOrBikePartBudgetList(laborOrBikePartBudgetList);
        budgetDTO.setStatus(budget.getStatus().getDescription());
        budgetDTO.setCreatedAt(budget.getCreatedAt());
        budgetDTO.setTotalValue(sumLaborOrBikePartBudgetValue(laborOrBikePartBudgetList));
        return budgetDTO;
    }

    private double sumLaborOrBikePartBudgetValue(List<LaborOrBikePartBudget> laborOrBikePartBudgetList){
        double sum = 0;
        for (LaborOrBikePartBudget laborOrBikePartBudget : laborOrBikePartBudgetList) {
            sum = sum + (laborOrBikePartBudget.getQuantity()*laborOrBikePartBudget.getValue());
        }
        return sum;
    }

    public void addNewBudget(CreateBudgetRequest createBudgetRequest) {
        if(budgetRepository.findBudgetNotFinishedByPlate(createBudgetRequest.getPlate(), Status.StatusEnum.FINISHED.id, Status.StatusEnum.CANCELED.id) != null){
            throw new ApiRequestException("Erro! Orçamento em andamento!");
        }
        Budget budget = new Budget();
        budget.setClient(clientRepository.findById(createBudgetRequest.getClientId()).get());
        budget.setBikeName(createBudgetRequest.getBikeName());
        budget.setBikeBrand(createBudgetRequest.getBikeBrand());
        budget.setEngineCapacity(createBudgetRequest.getEngineCapacity());
        budget.setYear(createBudgetRequest.getYear());
        budget.setPlate(createBudgetRequest.getPlate());
        budget.setStatus(statusRepository.findByDescription(createBudgetRequest.getStatus()));
        budget.setCreatedAt(LocalDateTime.now());
        try {
            budgetRepository.save(budget);
            for (LaborOrBikePartBudget laborOrBikePartBudget: createBudgetRequest.getLaborOrBikePartBudgetList()) {
                laborOrBikePartBudget.setBudget(budget);
                laborOrBikePartBudgetRepository.save(laborOrBikePartBudget);
            }
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o orçamento!");
        }
    }

    public void editBudgetById(EditBudgetRequest editBudgetRequest) {
        Budget budget = budgetRepository.findById(editBudgetRequest.getId()).get();
        budget.setStatus(statusRepository.findByDescription(editBudgetRequest.getStatus()));
        budget.setUpdatedAt(LocalDateTime.now());
        try {
            budgetRepository.save(budget);
            laborOrBikePartBudgetRepository.deleteAllByBudgetId(budget.getId());
            for (LaborOrBikePartBudget laborOrBikePartBudget: editBudgetRequest.getLaborOrBikePartBudgetList()) {
                laborOrBikePartBudget.setBudget(budget);
                laborOrBikePartBudgetRepository.save(laborOrBikePartBudget);
            }
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar o orçamento!");
        }
    }
}
