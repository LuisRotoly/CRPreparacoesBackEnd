package com.crpreparacoes.bodyrequestinput.bikePart;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBikePartRequest {
    private Long id;
    private String name;
    private Double value;
    private Double profitPercentage;
    private Integer stockQuantity;
    private Long bikeId;
}
