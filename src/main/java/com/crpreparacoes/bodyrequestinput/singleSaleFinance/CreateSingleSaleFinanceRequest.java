package com.crpreparacoes.bodyrequestinput.singleSaleFinance;

import com.crpreparacoes.models.PaymentFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSingleSaleFinanceRequest {
    private Long singleSaleId;
    private double paymentValue;
    private String notes;
    private PaymentFormat paymentFormat;
}
