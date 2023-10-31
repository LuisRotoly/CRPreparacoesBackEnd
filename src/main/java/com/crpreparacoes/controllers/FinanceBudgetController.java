package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.financeBudget.CreateFinanceBudgetRequest;
import com.crpreparacoes.dto.FinanceBudgetDTO;
import com.crpreparacoes.models.FinanceBudget;
import com.crpreparacoes.services.FinanceBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class FinanceBudgetController {

    @Autowired
    private FinanceBudgetService financeBudgetService;

    /**Método para buscar todos os pagamento dos orçamentos
     * @return List<FinanceBudgetDTO> - Lista de pagamentos dos orçamentos
     */
    @RequestMapping(value="/listFinanceBudgets", method = RequestMethod.GET)
    public @ResponseBody List<FinanceBudgetDTO> listFinanceBudgets(){
        return financeBudgetService.listAllFinanceBudgets();
    }

    /**Método para buscar os pagamentos dos orçamentos usando um filtro
     * @return List<FinanceBudgetDTO> - Lista de pagamentos dos orçamentos
     */
    @RequestMapping(value="/filterFinanceBudgetListRequest", method = RequestMethod.GET)
    public @ResponseBody List<FinanceBudgetDTO> filterFinanceBudgetListRequest(@RequestParam String word){
        return financeBudgetService.filterFinanceBudgetListRequest(word);
    }

    /**Método para buscar um pagamento de um orçamento
     * @return FinanceBudgetDTO - pagamento de um orçamento
     */
    @RequestMapping(value="/listFinanceBudgetById", method = RequestMethod.GET)
    public @ResponseBody FinanceBudgetDTO listFinanceBudgetById(@RequestParam Long id){
        return financeBudgetService.listFinanceBudgetById(id);
    }

    /**Método para buscar todos os pagamentos de um orçamento
     * @return List<FinanceBudget> - lista de pagamentos de um orçamento
     */
    @RequestMapping(value="/getPaymentsListById", method = RequestMethod.GET)
    public @ResponseBody List<FinanceBudget> getPaymentsListById(@RequestParam Long id){
        return financeBudgetService.getPaymentsListById(id);
    }

    /**Método para remover um pagamento
     */
    @RequestMapping(value="/removePaymentById", method = RequestMethod.DELETE)
    public void removePaymentById(@RequestParam Long id){
        financeBudgetService.removePaymentById(id);
    }

    /**Método para adicionar um pagamento
     */
    @RequestMapping(value="/addPayment", method = RequestMethod.POST)
    public void addPayment(@RequestBody CreateFinanceBudgetRequest createFinanceBudgetRequest){
        financeBudgetService.addPayment(createFinanceBudgetRequest);
    }

    /**Método para buscar o valor à ser pago
     * @return double - valor
     */
    @RequestMapping(value="/getToBePaidById", method = RequestMethod.GET)
    public @ResponseBody double getToBePaidById(@RequestParam Long id){
        return financeBudgetService.getToBePaidById(id);
    }
}
