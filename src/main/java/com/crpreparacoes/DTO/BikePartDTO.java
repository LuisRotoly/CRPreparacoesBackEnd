package com.crpreparacoes.DTO;

import com.crpreparacoes.models.Bike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BikePartDTO {
    private String name;
    private Double value;
    private Double profitPercentage;
    private Double finalValue;
    private Integer stockQuantity;
    private Bike bike;
}
