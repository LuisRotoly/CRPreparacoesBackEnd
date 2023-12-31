package com.crpreparacoes.dto;

import com.crpreparacoes.models.Bike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientBikeDTO {
    private String plate;
    private Bike bike;
    private String year;
}
