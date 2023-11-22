package com.crpreparacoes.bodyrequestinput.budget;

import com.crpreparacoes.models.LaborOrBikePartBudget;
import com.crpreparacoes.models.PaymentFormat;
import com.crpreparacoes.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBudgetRequest {
    private Long id;
    private PaymentFormat paymentFormat;
    private List<LaborOrBikePartBudget> laborOrBikePartBudgetList;
    private Integer discountPercentage;
    private Status status;
    private String notes;
    private String problems;
    private Integer kilometersDriven;
}
