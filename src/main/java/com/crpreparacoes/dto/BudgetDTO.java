package com.crpreparacoes.dto;

import com.crpreparacoes.models.Client;
import com.crpreparacoes.models.LaborOrBikePartBudget;
import com.crpreparacoes.models.PaymentFormat;
import com.crpreparacoes.models.Status;
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
    private PaymentFormat paymentFormat;
    private Integer kilometersDriven;
    private List<LaborOrBikePartBudget> laborOrBikePartBudgetList;
    private Integer discountPercentage;
    private Status status;
    private String notes;
    private LocalDateTime createdAt;
}
