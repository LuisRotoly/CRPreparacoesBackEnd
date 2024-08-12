package com.crpreparacoes.dto;

import com.crpreparacoes.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceBudgetDTO {
    private Long budgetId;
    private String clientName;
    private String plate;
    private String bikeNameAndBrand;
    private List<FinanceBudget> financeBudgetList;
    private LocalDateTime finalizedAt;
    private double totalValue;
    private double toBePaid;
    private String notes;
    private boolean isPaid;
}
