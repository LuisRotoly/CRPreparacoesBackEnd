package com.crpreparacoes.dto;

import com.crpreparacoes.models.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaborOrBikePartSingleSaleDTO {
    private Long id;
    private String client;
    private LocalDateTime createdAt;
    private Integer bikePartQuantity;
}
