package com.crpreparacoes.dto;

import com.crpreparacoes.models.Client;
import com.crpreparacoes.models.LaborOrBikePartBudget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {
    private Client client;
    private String plate;
    private String bikeName;
    private String bikeBrand;
    private Integer engineCapacity;
    private String year;
    private List<LaborOrBikePartBudget> laborOrBikePartBudgetList;
    private String status;
    private LocalDateTime createdAt;
    private double totalValue;
}
