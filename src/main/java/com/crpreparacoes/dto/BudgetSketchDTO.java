package com.crpreparacoes.dto;

import com.crpreparacoes.models.LaborOrBikePartBudgetSketch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetSketchDTO {
    private Long id;
    private String client;
    private String plate;
    private String bike;
    private String phone;
    private String notes;
    private LocalDateTime createdAt;
    private List<LaborOrBikePartBudgetSketch> laborOrBikePartBudgetSketchList;
}
