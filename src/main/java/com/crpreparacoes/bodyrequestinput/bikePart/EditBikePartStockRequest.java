package com.crpreparacoes.bodyrequestinput.bikePart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBikePartStockRequest {
    private Long id;
    private Integer stockQuantity;
}
