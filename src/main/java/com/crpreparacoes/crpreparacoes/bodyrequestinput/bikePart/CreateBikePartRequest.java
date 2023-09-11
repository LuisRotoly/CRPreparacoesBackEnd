package com.crpreparacoes.crpreparacoes.bodyrequestinput.bikePart;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBikePartRequest {
    private String name;
    private Double value;
    private Integer stockQuantity;
    private Integer bikeId;
}
