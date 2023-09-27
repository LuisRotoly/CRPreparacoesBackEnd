package com.crpreparacoes.bodyrequestinput.budget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBudgetRequest {
    private Long id;
    private String status;
}
