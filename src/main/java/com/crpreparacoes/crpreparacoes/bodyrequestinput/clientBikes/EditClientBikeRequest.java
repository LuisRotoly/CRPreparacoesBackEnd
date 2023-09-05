package com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBikes;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditClientBikeRequest {
    private Long id;
    private Integer clientId;
    private Integer bikeId;
    private String plate;
}
