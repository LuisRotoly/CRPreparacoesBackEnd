package com.crpreparacoes.bodyrequestinput.financeBudget;

import com.crpreparacoes.models.PaymentFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFinanceBudgetRequest {
    private Long budgetId;
    private double paymentValue;
    private PaymentFormat paymentFormat;
}
