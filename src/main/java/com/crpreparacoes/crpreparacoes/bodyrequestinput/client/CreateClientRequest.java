package com.crpreparacoes.crpreparacoes.bodyrequestinput.client;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientRequest {
    private String name;
    private String cpfcnpj;
    private String address;
    private String phone;
    private String nickname;
}
