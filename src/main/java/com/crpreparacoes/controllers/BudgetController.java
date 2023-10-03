package com.crpreparacoes.controllers;

import com.crpreparacoes.dto.BudgetDTO;
import com.crpreparacoes.bodyrequestinput.budget.CreateBudgetRequest;
import com.crpreparacoes.bodyrequestinput.budget.EditBudgetRequest;
import com.crpreparacoes.models.Budget;
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
     * @return List<Budget> - Lista de orçamentos
     */
    @RequestMapping(value="/listBudgets", method = RequestMethod.GET)
    public @ResponseBody List<Budget> listBudgets(){
        return budgetService.listAllBudgets();
    }

    /**Método para buscar os orçamentos usando um filtro
     * @return List<Budget> - Lista de orçamentos
     */
    @RequestMapping(value="/filterListBudgets", method = RequestMethod.GET)
    public @ResponseBody List<Budget> filterListBudgets(@RequestParam String word){
        return budgetService.filterListBudgets(word);
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
}
