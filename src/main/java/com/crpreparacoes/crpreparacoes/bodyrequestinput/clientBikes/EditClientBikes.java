package com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBikes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditClientBikes {
    private Long id;
    private Integer clientId;
    private Integer bikeId;
    private String plate;
}
