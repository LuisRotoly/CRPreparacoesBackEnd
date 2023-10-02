package com.crpreparacoes.bodyrequestinput.bikePart;

import com.crpreparacoes.models.Bike;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBikePartRequest {
    private String name;
    private Double value;
    private Double profitPercentage;
    private Integer stockQuantity;
    private List<Bike> bikeList;
}
