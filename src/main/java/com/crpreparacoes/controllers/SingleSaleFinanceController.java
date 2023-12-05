package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.singleSaleFinance.CreateSingleSaleFinanceRequest;
import com.crpreparacoes.dto.SingleSaleFinanceDTO;
import com.crpreparacoes.models.SingleSaleFinance;
import com.crpreparacoes.services.SingleSaleFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class SingleSaleFinanceController {

    @Autowired
    private SingleSaleFinanceService singleSaleFinanceService;

    /**Método para buscar todos os pagamento das vendas
     * @return List<SingleSaleFinanceDTO> - Lista de pagamentos das vendas
     */
    @RequestMapping(value="/listSingleSaleFinance", method = RequestMethod.GET)
    public @ResponseBody List<SingleSaleFinanceDTO> listSingleSaleFinance(){
        return singleSaleFinanceService.listAllSingleSaleFinance();
    }

    /**Método para buscar os pagamentos das vendas usando um filtro
     * @return List<SingleSaleFinanceDTO> - Lista de pagamentos das vendas
     */
    @RequestMapping(value="/filterSingleSaleFinanceListRequest", method = RequestMethod.GET)
    public @ResponseBody List<SingleSaleFinanceDTO> filterSingleSaleFinanceListRequest(@RequestParam String word, @RequestParam boolean isInDebit){
        return singleSaleFinanceService.filterSingleSaleFinanceListRequest(word, isInDebit);
    }

    /**Método para buscar um pagamento de uma venda
     * @return FinanceBudgetDTO - pagamento de uma venda
     */
    @RequestMapping(value="/listSingleSaleFinanceById", method = RequestMethod.GET)
    public @ResponseBody SingleSaleFinanceDTO listSingleSaleFinanceById(@RequestParam Long id){
        return singleSaleFinanceService.listSingleSaleFinanceById(id);
    }

    /**Método para buscar todos os pagamentos de uma venda
     * @return List<SingleSaleFinance> - lista de pagamentos de uma venda
     */
    @RequestMapping(value="/getSingleSalePaymentsListById", method = RequestMethod.GET)
    public @ResponseBody List<SingleSaleFinance> getSingleSalePaymentsListById(@RequestParam Long id){
        return singleSaleFinanceService.getSingleSalePaymentsListById(id);
    }

    /**Método para remover uma venda
     */
    @RequestMapping(value="/removeSingleSalePaymentById", method = RequestMethod.DELETE)
    public void removeSingleSalePaymentById(@RequestParam Long id){
        singleSaleFinanceService.removeSingleSalePaymentById(id);
    }

    /**Método para adicionar uma venda
     */
    @RequestMapping(value="/addSingleSalePayment", method = RequestMethod.POST)
    public void addSingleSalePayment(@RequestBody CreateSingleSaleFinanceRequest createSingleSaleFinanceRequest){
        singleSaleFinanceService.addSingleSalePayment(createSingleSaleFinanceRequest);
    }

    /**Método para buscar o valor à ser pago
     * @return double - valor
     */
    @RequestMapping(value="/getSingleSaleToBePaidById", method = RequestMethod.GET)
    public @ResponseBody double getSingleSaleToBePaidById(@RequestParam Long id){
        return singleSaleFinanceService.getSingleSaleToBePaidById(id);
    }

    /**Método para buscar o total que resta a receber
     * @return double - Valor total
     */
    @RequestMapping(value="/getSingleSaleTotalToReceive", method = RequestMethod.GET)
    public @ResponseBody double getSingleSaleTotalToReceive(){
        return singleSaleFinanceService.getSingleSaleTotalToReceive();
    }
}
