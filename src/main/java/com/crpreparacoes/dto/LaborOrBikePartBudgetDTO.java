package com.crpreparacoes.dto;

import com.crpreparacoes.models.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaborOrBikePartBudgetDTO {
    private Long id;
    private Client client;
    private String plate;
    private String bikeName;
    private String bikeBrand;
    private LocalDateTime createdAt;
    private Integer bikePartQuantity;
}
