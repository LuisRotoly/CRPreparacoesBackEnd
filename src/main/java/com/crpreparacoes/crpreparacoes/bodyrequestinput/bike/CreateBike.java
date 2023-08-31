package com.crpreparacoes.crpreparacoes.bodyrequestinput.bike;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBike {
    private String name;
    private String brand;
    private Integer engineCapacity;
    private String year;
}
