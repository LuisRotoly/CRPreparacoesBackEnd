package com.crpreparacoes.crpreparacoes.bodyrequestinput.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplier {
    private String name;
    private String phone;
    private String notes;
}
