package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.budget.AddLaborOrBikePartBudget;
import com.crpreparacoes.dto.BudgetDTO;
import com.crpreparacoes.dto.LaborOrBikePartBudgetDTO;
import com.crpreparacoes.models.LaborOrBikePartBudget;
import com.crpreparacoes.services.LaborOrBikePartBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class LaborOrBikePartBudgetController {

    @Autowired
    private LaborOrBikePartBudgetService laborOrBikePartBudgetService;

    /**Método para buscar todos os serviços pelo id do orçamento
     * @return List<LaborOrBikePartBudget> - Lista de serviços
     */
    @RequestMapping(value="/listLaborOrBikePartBudgetById", method = RequestMethod.GET)
    public @ResponseBody List<LaborOrBikePartBudget> listLaborOrBikePartBudgetById(@RequestParam Long budgetId){
        return laborOrBikePartBudgetService.listLaborOrBikePartBudgetById(budgetId);
    }

    /**Método para adicionar um serviço no orçamento
     */
    @RequestMapping(value="/addLaborOrBikePartBudget", method = RequestMethod.POST)
    public double addLaborOrBikePartBudget(@RequestBody AddLaborOrBikePartBudget addLaborOrBikePartBudget){
        return laborOrBikePartBudgetService.addLaborOrBikePartBudget(addLaborOrBikePartBudget);
    }

    /**Método para remover um serviço no orçamento
     */
    @RequestMapping(value="/removeLaborOrBikePartBudget", method = RequestMethod.DELETE)
    public double removeLaborOrBikePartBudget(@RequestParam Long id){
        return laborOrBikePartBudgetService.removeLaborOrBikePartBudget(id);
    }

    /**Método para buscar os orçamentos usando o id da peça da moto
     * @return List<LaborOrBikePartBudgetDTO> - Lista de orçamentos
     */
    @RequestMapping(value="/getBudgetHistoryByBikePartId", method = RequestMethod.GET)
    public @ResponseBody List<LaborOrBikePartBudgetDTO> getBudgetHistoryByBikePartId(@RequestParam Long bikePartId){
        return laborOrBikePartBudgetService.getBudgetHistoryByBikePartId(bikePartId);
    }
}
