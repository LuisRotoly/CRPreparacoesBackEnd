package com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBikes;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientBikeRequest {
    private Integer clientId;
    private Integer bikeId;
    private String plate;
}
