package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.budget.EditBudgetNotesRequest;
import com.crpreparacoes.dto.BudgetDTO;
import com.crpreparacoes.bodyrequestinput.budget.CreateBudgetRequest;
import com.crpreparacoes.bodyrequestinput.budget.EditBudgetRequest;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.*;
import com.crpreparacoes.models.BikeService;
import com.crpreparacoes.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
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

    @Autowired
    private BikeServiceService bikeServiceService;

    @Autowired
    private BikePartRepository bikePartRepository;

    @Autowired
    private PaymentFormatRepository paymentFormatRepository;

    private final LocalDateTime lastMonday = LocalDateTime.now().with(TemporalAdjusters.previous(DayOfWeek.MONDAY));

    public List<BudgetDTO> listAllBudgets() {
        List<Budget> budgetList = budgetRepository.listAllBudgets(lastMonday.minusWeeks(2));
        List<BudgetDTO> budgetDTOList = new ArrayList<>();
        for (Budget budget: budgetList) {
            BudgetDTO budgetDTO = new BudgetDTO();
            budgetDTO.setId(budget.getId());
            budgetDTO.setClient(budget.getClient());
            budgetDTO.setPlate(budget.getPlate());
            budgetDTO.setBikeName(budget.getBikeName());
            budgetDTO.setStatus(budget.getStatus());
            budgetDTO.setProblems(budget.getProblems());
            budgetDTO.setCreatedAt(budget.getCreatedAt());
            List<LaborOrBikePartBudget> laborOrBikePartBudgetList = laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(budget.getId());
            for (LaborOrBikePartBudget laborOrBikePartBudget:laborOrBikePartBudgetList) {
                budgetDTO.setTotalValue(budgetDTO.getTotalValue()+laborOrBikePartBudget.getValue()*laborOrBikePartBudget.getQuantity());
            }
            if(budget.getDiscountPercentage() != null){
                budgetDTO.setTotalValue(getValueWithDiscount(budgetDTO.getTotalValue(),budget.getDiscountPercentage()));
            }
            budgetDTOList.add(budgetDTO);
        }
        return budgetDTOList;
    }

    public List<BudgetDTO> filterListBudgets(String word, Long statusId){
        if(statusId == -1 && word.equals("")) {
            return listAllBudgets();
        }else if(statusId == -1){
             return convertBudgetToBudgetDTO(budgetRepository.filterListBudgetsWithoutStatus(word, lastMonday.minusWeeks(4)));
        }else if(word.equals("")){
            return convertBudgetToBudgetDTO(budgetRepository.filterListBudgetsWithoutWord(statusId, lastMonday.minusWeeks(4)));
        }else{
            return convertBudgetToBudgetDTO(budgetRepository.filterListBudgets(word, statusId));
        }
    }

    private List<BudgetDTO> convertBudgetToBudgetDTO(List<Budget> budgetList){
        List<BudgetDTO> budgetDTOList = new ArrayList<>();
        for (Budget budget: budgetList) {
            BudgetDTO budgetDTO = new BudgetDTO();
            budgetDTO.setId(budget.getId());
            budgetDTO.setClient(budget.getClient());
            budgetDTO.setPlate(budget.getPlate());
            budgetDTO.setBikeName(budget.getBikeName());
            budgetDTO.setStatus(budget.getStatus());
            budgetDTO.setCreatedAt(budget.getCreatedAt());
            List<LaborOrBikePartBudget> laborOrBikePartBudgetList = laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(budget.getId());
            for (LaborOrBikePartBudget laborOrBikePartBudget:laborOrBikePartBudgetList) {
                budgetDTO.setTotalValue(budgetDTO.getTotalValue()+laborOrBikePartBudget.getValue()*laborOrBikePartBudget.getQuantity());
            }
            if(budget.getDiscountPercentage() != null){
                budgetDTO.setTotalValue(getValueWithDiscount(budgetDTO.getTotalValue(),budget.getDiscountPercentage()));
            }
            budgetDTOList.add(budgetDTO);
        }
        return budgetDTOList;
    }

    public BudgetDTO listBudgetById(Long id) {
        Budget budget = budgetRepository.findById(id).get();
        List<LaborOrBikePartBudget> laborOrBikePartBudgetList = laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(id);
        BudgetDTO budgetDTO = new BudgetDTO();
        budgetDTO.setClient(budget.getClient());
        budgetDTO.setBikeName(budget.getBikeName());
        budgetDTO.setBikeBrand(budget.getBikeBrand());
        budgetDTO.setYear(budget.getYear());
        budgetDTO.setPaymentFormat(budget.getPaymentFormat());
        budgetDTO.setKilometersDriven(budget.getKilometersDriven());
        budgetDTO.setPlate(budget.getPlate());
        budgetDTO.setLaborOrBikePartBudgetList(laborOrBikePartBudgetList);
        budgetDTO.setDiscountPercentage(budget.getDiscountPercentage());
        budgetDTO.setStatus(budget.getStatus());
        budgetDTO.setNotes(budget.getNotes());
        budgetDTO.setProblems(budget.getProblems());
        budgetDTO.setCreatedAt(budget.getCreatedAt());
        List<BikeService> bikeServiceList = bikeServiceService.listAllBikeServices();
        for (LaborOrBikePartBudget laborOrBikePartBudget:laborOrBikePartBudgetList) {
            boolean match = false;
            for (BikeService bikeService:bikeServiceList) {
                if(laborOrBikePartBudget.getName().equals(bikeService.getName())){
                    budgetDTO.setTotalValueBikeService(budgetDTO.getTotalValueBikeService() + (laborOrBikePartBudget.getValue()* laborOrBikePartBudget.getQuantity()));
                    match = true;
                }
            }
            if(!match){
                budgetDTO.setTotalValueBikePart(budgetDTO.getTotalValueBikePart() + (laborOrBikePartBudget.getValue()* laborOrBikePartBudget.getQuantity()));
            }
        budgetDTO.setTotalValue(budgetDTO.getTotalValue() + (laborOrBikePartBudget.getValue()* laborOrBikePartBudget.getQuantity()));
        }
        if(budgetDTO.getDiscountPercentage() != null){
            budgetDTO.setTotalValue(getValueWithDiscount(budgetDTO.getTotalValue(),budgetDTO.getDiscountPercentage()));
        }
        return budgetDTO;
    }

    public void addNewBudget(CreateBudgetRequest createBudgetRequest) {
        if(budgetRepository.findBudgetNotFinishedByPlate(createBudgetRequest.getClientId(), createBudgetRequest.getPlate(), Status.StatusEnum.FINISHED.id, Status.StatusEnum.CANCELED.id) != null){
            throw new ApiRequestException("Erro! Orçamento em andamento!");
        }
        Budget budget = new Budget();
        budget.setClient(clientRepository.findById(createBudgetRequest.getClientId()).get());
        budget.setBikeName(createBudgetRequest.getBikeName());
        budget.setBikeBrand(createBudgetRequest.getBikeBrand());
        budget.setYear(createBudgetRequest.getYear());
        budget.setPaymentFormat(createBudgetRequest.getPaymentFormat());
        budget.setKilometersDriven(createBudgetRequest.getKilometersDriven());
        budget.setPlate(createBudgetRequest.getPlate());
        budget.setDiscountPercentage(createBudgetRequest.getDiscountPercentage());
        budget.setStatus(createBudgetRequest.getStatus());
        budget.setNotes(createBudgetRequest.getNotes());
        budget.setProblems(createBudgetRequest.getProblems());
        budget.setCreatedAt(LocalDateTime.now());
        budget.setUpdatedAt(LocalDateTime.now());
        try {
            budgetRepository.save(budget);
            if(createBudgetRequest.getStatus().equals(new Status(Status.StatusEnum.FINISHED))){
                for (LaborOrBikePartBudget laborOrBikePartBudget: createBudgetRequest.getLaborOrBikePartBudgetList()) {
                    BikePart bikePart = bikePartRepository.findByName(laborOrBikePartBudget.getName());
                    if(bikePart != null) {
                        bikePart.setStockQuantity(bikePart.getStockQuantity() - laborOrBikePartBudget.getQuantity());
                        bikePartRepository.save(bikePart);
                    }
                    laborOrBikePartBudget.setBudget(budget);
                    laborOrBikePartBudgetRepository.save(laborOrBikePartBudget);
                }
            }else{
                for (LaborOrBikePartBudget laborOrBikePartBudget: createBudgetRequest.getLaborOrBikePartBudgetList()) {
                    laborOrBikePartBudget.setBudget(budget);
                    laborOrBikePartBudgetRepository.save(laborOrBikePartBudget);
                }
            }
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o orçamento!");
        }
    }

    public void editBudgetById(EditBudgetRequest editBudgetRequest) {
        Budget budget = budgetRepository.findById(editBudgetRequest.getId()).get();
        budget.setPaymentFormat(editBudgetRequest.getPaymentFormat());
        budget.setStatus(editBudgetRequest.getStatus());
        budget.setDiscountPercentage(editBudgetRequest.getDiscountPercentage());
        budget.setNotes(editBudgetRequest.getNotes());
        budget.setProblems(editBudgetRequest.getProblems());
        budget.setUpdatedAt(LocalDateTime.now());
        budget.setKilometersDriven(editBudgetRequest.getKilometersDriven());
        try {
            budgetRepository.save(budget);
            laborOrBikePartBudgetRepository.deleteAllByBudgetId(budget.getId());
            if(editBudgetRequest.getStatus().equals(new Status(Status.StatusEnum.FINISHED))) {
                for (LaborOrBikePartBudget laborOrBikePartBudget : editBudgetRequest.getLaborOrBikePartBudgetList()) {
                    BikePart bikePart = bikePartRepository.findByName(laborOrBikePartBudget.getName());
                    if(bikePart != null) {
                        bikePart.setStockQuantity(bikePart.getStockQuantity() - laborOrBikePartBudget.getQuantity());
                        bikePartRepository.save(bikePart);
                    }
                    laborOrBikePartBudget.setBudget(budget);
                    laborOrBikePartBudgetRepository.save(laborOrBikePartBudget);
                }
            }else{
                for (LaborOrBikePartBudget laborOrBikePartBudget : editBudgetRequest.getLaborOrBikePartBudgetList()) {
                    laborOrBikePartBudget.setBudget(budget);
                    laborOrBikePartBudgetRepository.save(laborOrBikePartBudget);
                }
            }
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar o orçamento!");
        }
    }

    public Client findClientByBudgetId(Long budgetId) {
        return budgetRepository.findById(budgetId).get().getClient();
    }

    public void removeBudgetById(Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId).get();
        if(!budget.getStatus().equals(new Status(Status.StatusEnum.FINISHED))){
            budget.setStatus(new Status(Status.StatusEnum.CANCELED));
        }
        budget.setRemoved(true);
        try {
            budgetRepository.save(budget);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar remover o orçamento!");
        }
    }

    public void editBudgetNotesById(EditBudgetNotesRequest editBudgetNotesRequest) {
        Budget budget = budgetRepository.findById(editBudgetNotesRequest.getId()).get();
        budget.setNotes(editBudgetNotesRequest.getNotes());
        try {
            budgetRepository.save(budget);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar alterar as observações do orçamento!");
        }
    }

    private double getValueWithDiscount(double totalValue, Integer discount){
        return totalValue - (totalValue*discount/100);
    }

    public List<BudgetDTO> listBudgetByClientId(Long clientId) {
        List<Budget> budgetList = budgetRepository.listAllBudgetsByClientId(clientId);
        List<BudgetDTO> budgetDTOList = new ArrayList<>();
        for (Budget budget: budgetList) {
            BudgetDTO budgetDTO = new BudgetDTO();
            budgetDTO.setId(budget.getId());
            budgetDTO.setPlate(budget.getPlate());
            budgetDTO.setBikeBrand(budget.getBikeBrand());
            budgetDTO.setBikeName(budget.getBikeName());
            budgetDTO.setStatus(budget.getStatus());
            budgetDTO.setCreatedAt(budget.getCreatedAt());
            List<LaborOrBikePartBudget> laborOrBikePartBudgetList = laborOrBikePartBudgetRepository.findAllLaborOrBikePartBudgetById(budget.getId());
            for (LaborOrBikePartBudget laborOrBikePartBudget:laborOrBikePartBudgetList) {
                budgetDTO.setTotalValue(budgetDTO.getTotalValue()+laborOrBikePartBudget.getValue()*laborOrBikePartBudget.getQuantity());
            }
            if(budget.getDiscountPercentage() != null){
                budgetDTO.setTotalValue(getValueWithDiscount(budgetDTO.getTotalValue(),budget.getDiscountPercentage()));
            }
            budgetDTOList.add(budgetDTO);
        }
        return budgetDTOList;
    }
}
