package com.crpreparacoes.bodyrequestinput.bike;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBikeRequest {
    private String name;
    private Long brandId;
}
