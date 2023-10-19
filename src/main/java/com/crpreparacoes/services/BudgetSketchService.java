package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.budgetSketch.CreateBudgetSketchRequest;
import com.crpreparacoes.bodyrequestinput.budgetSketch.EditBudgetSketchRequest;
import com.crpreparacoes.dto.BudgetSketchDTO;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.BudgetSketch;
import com.crpreparacoes.models.LaborOrBikePartBudgetSketch;
import com.crpreparacoes.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BudgetSketchService {
    @Autowired
    private BudgetSketchRepository budgetSketchRepository;

    @Autowired
    private LaborOrBikePartBudgetSketchRepository laborOrBikePartBudgetSketchRepository;

    public List<BudgetSketch> listAllBudgetsSketch() {
        return budgetSketchRepository.listAllBudgetsSketch();
    }

    public List<BudgetSketch> filterListBudgetsSketch(String word) {
        return budgetSketchRepository.filterListBudgetsSketch(word);
    }

    public BudgetSketchDTO listBudgetSketchById(Long id) {
        BudgetSketch budgetSketch = budgetSketchRepository.findById(id).get();
        List<LaborOrBikePartBudgetSketch> laborOrBikePartBudgetSketchList = laborOrBikePartBudgetSketchRepository.findAllLaborOrBikePartBudgetSketchById(id);
        BudgetSketchDTO budgetSketchDTO = new BudgetSketchDTO();
        budgetSketchDTO.setClient(budgetSketch.getClient());
        budgetSketchDTO.setBike(budgetSketch.getBike());
        budgetSketchDTO.setPlate(budgetSketch.getPlate());
        budgetSketchDTO.setPhone(budgetSketch.getPhone());
        budgetSketchDTO.setNotes(budgetSketch.getNotes());
        budgetSketchDTO.setCreatedAt(budgetSketch.getCreatedAt());
        budgetSketchDTO.setLaborOrBikePartBudgetSketchList(laborOrBikePartBudgetSketchList);
        return budgetSketchDTO;
    }

    public void addNewBudgetSketch(CreateBudgetSketchRequest createBudgetSketchRequest) {
        BudgetSketch budgetSketch = new BudgetSketch();
        budgetSketch.setClient(createBudgetSketchRequest.getClient());
        budgetSketch.setBike(createBudgetSketchRequest.getBike());
        budgetSketch.setPlate(createBudgetSketchRequest.getPlate());
        budgetSketch.setPhone(createBudgetSketchRequest.getPhone());
        budgetSketch.setNotes(createBudgetSketchRequest.getNotes());
        budgetSketch.setCreatedAt(LocalDateTime.now());
        try {
            budgetSketchRepository.save(budgetSketch);
            for (LaborOrBikePartBudgetSketch laborOrBikePartBudgetSketch: createBudgetSketchRequest.getLaborOrBikePartBudgetSketchList()) {
                laborOrBikePartBudgetSketch.setBudgetSketch(budgetSketch);
                laborOrBikePartBudgetSketchRepository.save(laborOrBikePartBudgetSketch);
            }
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o orçamento!");
        }
    }

    public void editBudgetSketchById(EditBudgetSketchRequest editBudgetSketchRequest) {
        BudgetSketch budgetSketch = budgetSketchRepository.findById(editBudgetSketchRequest.getId()).get();
        budgetSketch.setBike(editBudgetSketchRequest.getBike());
        budgetSketch.setPlate(editBudgetSketchRequest.getPlate());
        budgetSketch.setPhone(editBudgetSketchRequest.getPhone());
        budgetSketch.setNotes(editBudgetSketchRequest.getNotes());
        budgetSketch.setUpdatedAt(LocalDateTime.now());
        try {
            budgetSketchRepository.save(budgetSketch);
            laborOrBikePartBudgetSketchRepository.deleteAllByBudgetSketchId(budgetSketch.getId());
            for (LaborOrBikePartBudgetSketch laborOrBikePartBudgetSketch: editBudgetSketchRequest.getLaborOrBikePartBudgetSketchList()) {
                laborOrBikePartBudgetSketch.setBudgetSketch(budgetSketch);
                laborOrBikePartBudgetSketchRepository.save(laborOrBikePartBudgetSketch);
            }
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar o orçamento!");
        }
    }

    public void removeBudgetSketchById(Long budgetSketchId) {
        try {
            laborOrBikePartBudgetSketchRepository.deleteAllByBudgetSketchId(budgetSketchId);
            budgetSketchRepository.deleteById(budgetSketchId);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar remover o orçamento!");
        }
    }
}
