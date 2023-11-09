package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.budgetSketch.CreateBudgetSketchRequest;
import com.crpreparacoes.bodyrequestinput.budgetSketch.EditBudgetSketchRequest;
import com.crpreparacoes.dto.BudgetSketchDTO;
import com.crpreparacoes.models.BudgetSketch;
import com.crpreparacoes.services.BudgetSketchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BudgetSketchController {

    @Autowired
    private BudgetSketchService budgetSketchService;

    /**Método para buscar todos os orçamentos avulsos
     * @return List<BudgetSketchDTO> - Lista de orçamentos avulsos
     */
    @RequestMapping(value="/listBudgetsSketch", method = RequestMethod.GET)
    public @ResponseBody List<BudgetSketchDTO> listBudgetsSketch(){
        return budgetSketchService.listAllBudgetsSketch();
    }

    /**Método para buscar os orçamentos avulsos usando um filtro
     * @return List<BudgetSketchDTO> - Lista de orçamentos avulsos
     */
    @RequestMapping(value="/filterListBudgetsSketch", method = RequestMethod.GET)
    public @ResponseBody List<BudgetSketchDTO> filterListBudgetsSketch(@RequestParam String word){
        return budgetSketchService.filterListBudgetsSketch(word);
    }

    /**Método para buscar um orçamento avulso
     * @return BudgetSketch - orçamento avulso
     */
    @RequestMapping(value="/listBudgetSketchById", method = RequestMethod.GET)
    public @ResponseBody BudgetSketchDTO listBudgetSketchById(@RequestParam Long id){
        return budgetSketchService.listBudgetSketchById(id);
    }

    /**Método para adicionar um orçamento avulso
     */
    @RequestMapping(value="/addBudgetSketch", method = RequestMethod.POST)
    public void addBudgetSketch(@RequestBody CreateBudgetSketchRequest createBudgetSketchRequest){
        budgetSketchService.addNewBudgetSketch(createBudgetSketchRequest);
    }

    /**Método para editar um orçamento avulso
     */
    @RequestMapping(value="/editBudgetSketch", method = RequestMethod.POST)
    public void editBudgetSketch(@RequestBody EditBudgetSketchRequest editBudgetSketchRequest){
        budgetSketchService.editBudgetSketchById(editBudgetSketchRequest);
    }

    /**Método para remover um orçamento avulso
     */
    @RequestMapping(value="/removeBudgetSketchById", method = RequestMethod.PUT)
    public void removeBudgetSketchById(@RequestParam Long budgetSketchId){
        budgetSketchService.removeBudgetSketchById(budgetSketchId);
    }

    /**Método para buscar o custo de uma peça ou serviço
     * @return double - custo da peça ou serviço
     */
    @RequestMapping(value="/findLaborOrBikePartByName", method = RequestMethod.GET)
    public @ResponseBody double findLaborOrBikePartByName(@RequestParam String name){
        return budgetSketchService.findLaborOrBikePartByName(name);
    }
}
