package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.budgetSketch.CreateBudgetSketchRequest;
import com.crpreparacoes.bodyrequestinput.budgetSketch.EditBudgetSketchRequest;
import com.crpreparacoes.dto.BudgetDTO;
import com.crpreparacoes.dto.BudgetSketchDTO;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.*;
import com.crpreparacoes.models.BikeService;
import com.crpreparacoes.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetSketchService {
    @Autowired
    private BudgetSketchRepository budgetSketchRepository;

    @Autowired
    private BikePartRepository bikePartRepository;

    @Autowired
    private BikeServiceRepository bikeServiceRepository;

    @Autowired
    private BikeServiceService bikeServiceService;

    @Autowired
    private LaborOrBikePartBudgetSketchRepository laborOrBikePartBudgetSketchRepository;

    public List<BudgetSketchDTO> listAllBudgetsSketch() {
        List<BudgetSketch> budgetSketchList =  budgetSketchRepository.listAllBudgetsSketch();
        List<BudgetSketchDTO> budgetSketchDTOListDTOList = new ArrayList<>();
        for (BudgetSketch budgetSketch: budgetSketchList) {
            BudgetSketchDTO budgetSketchDTO = new BudgetSketchDTO();
            budgetSketchDTO.setId(budgetSketch.getId());
            budgetSketchDTO.setClient(budgetSketch.getClient());
            budgetSketchDTO.setPlate(budgetSketch.getPlate());
            budgetSketchDTO.setBike(budgetSketch.getBike());
            budgetSketchDTO.setCreatedAt(budgetSketch.getCreatedAt());
            List<LaborOrBikePartBudgetSketch> laborOrBikePartBudgetSketchList = laborOrBikePartBudgetSketchRepository.findAllLaborOrBikePartBudgetSketchById(budgetSketch.getId());
            for (LaborOrBikePartBudgetSketch laborOrBikePartBudgetSketch:laborOrBikePartBudgetSketchList) {
                budgetSketchDTO.setTotalValue(budgetSketchDTO.getTotalValue()+laborOrBikePartBudgetSketch.getValue()*laborOrBikePartBudgetSketch.getQuantity());
            }
            budgetSketchDTOListDTOList.add(budgetSketchDTO);
        }
        return budgetSketchDTOListDTOList;
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
        List<BikeService> bikeServiceList = bikeServiceService.listAllBikeServices();
        for (LaborOrBikePartBudgetSketch laborOrBikePartBudgetSketch:laborOrBikePartBudgetSketchList) {
            boolean match = false;
            for (BikeService bikeService:bikeServiceList) {
                if(laborOrBikePartBudgetSketch.getName().equals(bikeService.getName())){
                    budgetSketchDTO.setTotalValueBikeService(budgetSketchDTO.getTotalValueBikeService() + (laborOrBikePartBudgetSketch.getValue()* laborOrBikePartBudgetSketch.getQuantity()));
                    match = true;
                }
            }
            if(!match){
                budgetSketchDTO.setTotalValueBikePart(budgetSketchDTO.getTotalValueBikePart() + (laborOrBikePartBudgetSketch.getValue()* laborOrBikePartBudgetSketch.getQuantity()));
            }
            budgetSketchDTO.setTotalValue(budgetSketchDTO.getTotalValue() + (laborOrBikePartBudgetSketch.getValue()* laborOrBikePartBudgetSketch.getQuantity()));
        }
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

    public double findLaborOrBikePartByName(String name) {
        BikeService bikeService = bikeServiceRepository.findByName(name);
        if(bikeService != null){
            return bikeService.getValue();
        }else {
            return bikePartRepository.findByName(name).getValue();
        }
    }
}
