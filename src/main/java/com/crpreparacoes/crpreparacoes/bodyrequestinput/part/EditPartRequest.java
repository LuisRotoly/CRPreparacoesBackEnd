package com.crpreparacoes.crpreparacoes.bodyrequestinput.part;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditPartRequest {
    private Long id;
    private String name;
    private Double value;
    private Integer stockQuantity;
    private Integer bikeId;
}
