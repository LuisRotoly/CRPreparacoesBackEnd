package com.crpreparacoes.dto;

import com.crpreparacoes.models.LaborOrBikePartBudget;
import com.crpreparacoes.models.SingleSaleRelBikePart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleSaleDTO {
    private String clientName;
    private List<SingleSaleRelBikePart> singleSaleRelBikePartList;
    private LocalDateTime createdAt;
    private double totalValue;
}
