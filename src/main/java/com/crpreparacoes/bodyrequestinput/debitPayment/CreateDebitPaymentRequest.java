package com.crpreparacoes.bodyrequestinput.debitPayment;

import com.crpreparacoes.models.PaymentFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDebitPaymentRequest {
    private String description;
    private String notes;
    private double paymentValue;
    private PaymentFormat paymentFormat;
}
