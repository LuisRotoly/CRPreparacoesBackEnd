package com.crpreparacoes.crpreparacoes.bodyrequestinput.bike;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBikeRequest {
    private String name;
    private String brand;
    private Integer engineCapacity;
    private String year;
}
