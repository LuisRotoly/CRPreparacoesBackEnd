package com.crpreparacoes.bodyrequestinput.bikePart;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBikePartRequest {
    private String name;
    private Double value;
    private Double profitPercentage;
    private Integer stockQuantity;
    private Long bikeId;
}
