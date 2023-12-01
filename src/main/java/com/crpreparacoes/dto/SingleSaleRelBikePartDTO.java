package com.crpreparacoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleSaleRelBikePartDTO {
    private Long id;
    private String client;
    private LocalDateTime createdAt;
    private Integer bikePartQuantity;
}
