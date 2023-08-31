package com.crpreparacoes.crpreparacoes.bodyrequestinput.part;

import com.crpreparacoes.crpreparacoes.models.Bike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePart {
    private String name;
    private Double value;
    private Integer stockQuantity;
    private Integer bikeId;
}
