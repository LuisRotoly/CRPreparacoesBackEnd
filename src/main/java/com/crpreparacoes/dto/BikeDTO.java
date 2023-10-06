package com.crpreparacoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BikeDTO {
    private String brandName;
    private String name;
    private Integer engineCapacity;
    private String year;
}
