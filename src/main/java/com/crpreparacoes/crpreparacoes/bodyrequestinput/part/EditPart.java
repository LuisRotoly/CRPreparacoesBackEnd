package com.crpreparacoes.crpreparacoes.bodyrequestinput.part;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditPart {
    private Long id;
    private String name;
    private Double value;
    private Integer stockQuantity;
    private Integer bikeId;
}
