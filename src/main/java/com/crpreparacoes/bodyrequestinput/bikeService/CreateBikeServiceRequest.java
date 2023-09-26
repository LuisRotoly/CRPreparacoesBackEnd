package com.crpreparacoes.bodyrequestinput.bikeService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBikeServiceRequest {
    private String name;
    private Double value;
}
