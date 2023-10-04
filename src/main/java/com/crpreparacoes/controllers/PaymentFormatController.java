package com.crpreparacoes.controllers;

import com.crpreparacoes.models.PaymentFormat;
import com.crpreparacoes.services.PaymentFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PaymentFormatController {

    @Autowired
    private PaymentFormatService paymentFormatService;

    /**Método para buscar todos os tipos de pagamento
     * @return List<PaymentFormat> - Lista de tipos de pagamento
     */
    @RequestMapping(value="/listPaymentFormat", method = RequestMethod.GET)
    public @ResponseBody List<PaymentFormat> listPaymentFormat(){
        return paymentFormatService.listAllPaymentFormat();
    }
}
