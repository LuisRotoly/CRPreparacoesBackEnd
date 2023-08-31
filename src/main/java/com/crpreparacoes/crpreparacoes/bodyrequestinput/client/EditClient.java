package com.crpreparacoes.crpreparacoes.bodyrequestinput.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditClient {
    private Long id;
    private String name;
    private String cpfcnpj;
    private String address;
    private String phone;
    private String nickname;
}
