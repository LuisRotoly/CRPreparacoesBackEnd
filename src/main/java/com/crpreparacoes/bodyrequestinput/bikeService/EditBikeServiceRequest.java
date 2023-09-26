package com.crpreparacoes.bodyrequestinput.bikeService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBikeServiceRequest {
    private Long id;
    private String name;
    private Double value;
}
