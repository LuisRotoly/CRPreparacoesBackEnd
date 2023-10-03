package com.crpreparacoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BikeDTO {
    private String bikeBrandName;
    private String bikeName;
    private Integer engineCapacity;
    private String year;
}
