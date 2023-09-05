package com.crpreparacoes.crpreparacoes.bodyrequestinput.supplier;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplierRequest {
    private String name;
    private String phone;
    private String notes;
}
