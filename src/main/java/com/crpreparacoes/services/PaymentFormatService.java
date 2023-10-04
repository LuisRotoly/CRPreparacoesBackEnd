package com.crpreparacoes.services;

import com.crpreparacoes.models.PaymentFormat;
import com.crpreparacoes.repositories.PaymentFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentFormatService {

    @Autowired
    private PaymentFormatRepository paymentFormatRepository;

    public List<PaymentFormat> listAllPaymentFormat() {
        return paymentFormatRepository.listAllPaymentFormat();
    }
}
