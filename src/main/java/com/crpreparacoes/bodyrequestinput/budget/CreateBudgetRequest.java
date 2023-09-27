package com.crpreparacoes.bodyrequestinput.budget;

import com.crpreparacoes.models.LaborOrBikePartBudget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBudgetRequest {
    private Long clientId;
    private String plate;
    private String bikeName;
    private String bikeBrand;
    private Integer engineCapacity;
    private String year;
    private List<LaborOrBikePartBudget> laborOrBikePartBudgetList;
    private String status;
    private double totalValue;
}
