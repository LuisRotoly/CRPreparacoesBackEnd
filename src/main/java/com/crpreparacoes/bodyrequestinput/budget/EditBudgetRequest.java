package com.crpreparacoes.bodyrequestinput.budget;

import com.crpreparacoes.models.LaborOrBikePartBudget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBudgetRequest {
    private Long id;
    private String payment;
    private List<LaborOrBikePartBudget> laborOrBikePartBudgetList;
    private String status;
    private String notes;
}
