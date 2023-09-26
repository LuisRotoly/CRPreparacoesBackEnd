package com.crpreparacoes.bodyrequestinput.client;

import com.crpreparacoes.DTO.ClientBikeDTO;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientRequest {
    private String name;
    private String cpfcnpj;
    private String address;
    private String phone;
    private String nickname;
    private List<ClientBikeDTO> clientBikeList;
}
