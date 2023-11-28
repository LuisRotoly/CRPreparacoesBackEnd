package com.crpreparacoes.bodyrequestinput.singleSale;

import com.crpreparacoes.models.LaborOrBikePartSingleSale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSingleSaleRequest {
    private String client;
    private List<LaborOrBikePartSingleSale> laborOrBikePartList;
}
