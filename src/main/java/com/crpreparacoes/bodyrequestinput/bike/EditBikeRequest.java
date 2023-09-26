package com.crpreparacoes.bodyrequestinput.bike;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBikeRequest {
    private Long id;
    private String name;
    private Long brandId;
    private Integer engineCapacity;
    private String year;
}
