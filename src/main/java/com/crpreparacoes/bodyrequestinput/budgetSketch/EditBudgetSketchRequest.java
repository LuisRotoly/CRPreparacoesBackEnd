package com.crpreparacoes.bodyrequestinput.budgetSketch;

import com.crpreparacoes.models.LaborOrBikePartBudgetSketch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBudgetSketchRequest {
    private Long id;
    private String plate;
    private String bike;
    private String phone;
    private String notes;
    private List<LaborOrBikePartBudgetSketch> laborOrBikePartBudgetSketchList;
}
