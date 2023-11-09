package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.budget.EditBudgetNotesRequest;
import com.crpreparacoes.dto.BudgetDTO;
import com.crpreparacoes.bodyrequestinput.budget.CreateBudgetRequest;
import com.crpreparacoes.bodyrequestinput.budget.EditBudgetRequest;
import com.crpreparacoes.models.Budget;
import com.crpreparacoes.models.Client;
import com.crpreparacoes.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    /**Método para buscar todos os orçamentos
     * @return List<BudgetDTO> - Lista de orçamentos
     */
    @RequestMapping(value="/listBudgets", method = RequestMethod.GET)
    public @ResponseBody List<BudgetDTO> listBudgets(){
        return budgetService.listAllBudgets();
    }

    /**Método para buscar os orçamentos usando um filtro
     * @return List<BudgetDTO> - Lista de orçamentos
     */
    @RequestMapping(value="/filterListBudgets", method = RequestMethod.GET)
    public @ResponseBody List<BudgetDTO> filterListBudgets(@RequestParam String word, @RequestParam Long statusId){
        return budgetService.filterListBudgets(word, statusId);
    }

    /**Método para buscar um orçamento
     * @return BudgetDTO - orçamento
     */
    @RequestMapping(value="/listBudgetById", method = RequestMethod.GET)
    public @ResponseBody BudgetDTO listBudgetById(@RequestParam Long id){
        return budgetService.listBudgetById(id);
    }

    /**Método para adicionar um orçamento
     */
    @RequestMapping(value="/addBudget", method = RequestMethod.POST)
    public void addBudget(@RequestBody CreateBudgetRequest createBudgetRequest){
        budgetService.addNewBudget(createBudgetRequest);
    }

    /**Método para editar um orçamento
     */
    @RequestMapping(value="/editBudget", method = RequestMethod.POST)
    public void editBudget(@RequestBody EditBudgetRequest editBudgetRequest){
        budgetService.editBudgetById(editBudgetRequest);
    }

    /**Método para buscar os dados do cliente usando id do orçamento
     * @return Client - cliente
     */
    @RequestMapping(value="/findClientByBudgetId", method = RequestMethod.GET)
    public @ResponseBody Client findClientByBudgetId(@RequestParam Long budgetId){
        return budgetService.findClientByBudgetId(budgetId);
    }

    /**Método para remover um orçamento
     */
    @RequestMapping(value="/removeBudgetById", method = RequestMethod.PUT)
    public void removeBudgetById(@RequestParam Long budgetId){
        budgetService.removeBudgetById(budgetId);
    }

    /**Método para editar as observações de um orçamento
     */
    @RequestMapping(value="/editBudgetNotesById", method = RequestMethod.PUT)
    public void editBudgetNotesById(@RequestBody EditBudgetNotesRequest editBudgetNotesRequest){
        budgetService.editBudgetNotesById(editBudgetNotesRequest);
    }
}
