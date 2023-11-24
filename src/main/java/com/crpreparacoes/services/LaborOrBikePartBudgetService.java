package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.budget.AddLaborOrBikePartBudget;
import com.crpreparacoes.dto.BudgetDTO;
import com.crpreparacoes.dto.LaborOrBikePartBudgetDTO;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.Budget;
import com.crpreparacoes.models.LaborOrBikePartBudget;
import com.crpreparacoes.models.Status;
import com.crpreparacoes.repositories.BikePartRepository;
import com.crpreparacoes.repositories.BudgetRepository;
import com.crpreparacoes.repositories.LaborOrBikePartBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LaborOrBikePartBudgetService {

    @Autowired
    private LaborOrBikePartBudgetRepository laborOrBikePartBudgetRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BikePartRepository bikePartRepository;

    public List<LaborOrBikePartBudget> listLaborOrBikePartBudgetById(Long budgetId) {
        return laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(budgetId);
    }

    public double addLaborOrBikePartBudget(AddLaborOrBikePartBudget addLaborOrBikePartBudget) {
        Budget budget = budgetRepository.findById(addLaborOrBikePartBudget.getLaborOrBikePartBudget().getBudget().getId()).get();
        double totalValue = sumLaborOrBikePartBudgetValue(laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(addLaborOrBikePartBudget.getLaborOrBikePartBudget().getBudget().getId()));
        budget.setUpdatedAt(LocalDateTime.now());
        try {
            laborOrBikePartBudgetRepository.save(addLaborOrBikePartBudget.getLaborOrBikePartBudget());
            budgetRepository.save(budget);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o serviço!");
        }
        return totalValue + (addLaborOrBikePartBudget.getLaborOrBikePartBudget().getQuantity()*addLaborOrBikePartBudget.getLaborOrBikePartBudget().getValue());
    }

    public double removeLaborOrBikePartBudget(Long id) {
        LaborOrBikePartBudget laborOrBikePartBudget = laborOrBikePartBudgetRepository.findById(id).get();
        Budget budget = budgetRepository.findById(laborOrBikePartBudget.getBudget().getId()).get();
        double totalValue = sumLaborOrBikePartBudgetValue(laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(laborOrBikePartBudget.getBudget().getId()));
        budget.setUpdatedAt(LocalDateTime.now());
        try {
            laborOrBikePartBudgetRepository.delete(laborOrBikePartBudget);
            budgetRepository.save(budget);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar remover o serviço!");
        }
        return totalValue - (laborOrBikePartBudget.getQuantity()*laborOrBikePartBudget.getValue());
    }

    private double sumLaborOrBikePartBudgetValue(List<LaborOrBikePartBudget> laborOrBikePartBudgetList){
        double sum = 0;
        for (LaborOrBikePartBudget laborOrBikePartBudget : laborOrBikePartBudgetList) {
            sum = sum + (laborOrBikePartBudget.getQuantity()*laborOrBikePartBudget.getValue());
        }
        return sum;
    }

    public List<LaborOrBikePartBudgetDTO> getBudgetHistoryByBikePartId(Long bikePartId) {
        List<LaborOrBikePartBudget> laborOrBikePartBudgetList = laborOrBikePartBudgetRepository.getLaborOrBikePartBudgetByBikePartId(bikePartRepository.findById(bikePartId).get().getName(), Status.StatusEnum.FINISHED.id);
        List<LaborOrBikePartBudgetDTO> laborOrBikePartBudgetDTOList = new ArrayList<>();
        for (LaborOrBikePartBudget laborOrBikePartBudget: laborOrBikePartBudgetList) {
            LaborOrBikePartBudgetDTO laborOrBikePartBudgetDTO = new LaborOrBikePartBudgetDTO();
            laborOrBikePartBudgetDTO.setId(laborOrBikePartBudget.getId());
            laborOrBikePartBudgetDTO.setClient(laborOrBikePartBudget.getBudget().getClient());
            laborOrBikePartBudgetDTO.setBikeName(laborOrBikePartBudget.getBudget().getBikeName());
            laborOrBikePartBudgetDTO.setBikeBrand(laborOrBikePartBudget.getBudget().getBikeBrand());
            laborOrBikePartBudgetDTO.setPlate(laborOrBikePartBudget.getBudget().getPlate());
            laborOrBikePartBudgetDTO.setCreatedAt(laborOrBikePartBudget.getBudget().getCreatedAt());
            laborOrBikePartBudgetDTO.setBikePartQuantity(laborOrBikePartBudget.getQuantity());
            laborOrBikePartBudgetDTOList.add(laborOrBikePartBudgetDTO);
        }
        return laborOrBikePartBudgetDTOList;
    }
}
