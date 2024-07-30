package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.debitPayment.CreateDebitPaymentRequest;
import com.crpreparacoes.dto.CashHandlingDTO;
import com.crpreparacoes.models.DebitPayment;
import com.crpreparacoes.services.DebitPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class DebitPaymentController {

    @Autowired
    private DebitPaymentService debitPaymentService;

    /**Método para remover um pagamento
     */
    @RequestMapping(value="/removeDebitPaymentById", method = RequestMethod.DELETE)
    public void removeDebitPaymentById(@RequestParam Long id){
        debitPaymentService.removeDebitPaymentById(id);
    }

    /**Método para adicionar um pagamento
     */
    @RequestMapping(value="/addDebitPayment", method = RequestMethod.POST)
    public void addDebitPayment(@RequestBody CreateDebitPaymentRequest createDebitPaymentRequest){
        debitPaymentService.addDebitPayment(createDebitPaymentRequest);
    }

    /**Método para buscar todos os pagamentos dada uma data
     * @return List<CashHandlingDTO> - lista de débitos e créditos
     */
    @RequestMapping(value="/getCashHandlingListByDate", method = RequestMethod.GET)
    public @ResponseBody List<CashHandlingDTO> getCashHandlingListByDate(@RequestParam String date){
        return debitPaymentService.getCashHandlingListByDate(date);
    }
}
