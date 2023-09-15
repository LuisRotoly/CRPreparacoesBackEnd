package com.crpreparacoes.crpreparacoes.bodyrequestinput.client;

import com.crpreparacoes.crpreparacoes.DTO.ClientBikeDTO;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditClientRequest {
    private Long id;
    private String name;
    private String cpfcnpj;
    private String address;
    private String phone;
    private String nickname;
    private List<ClientBikeDTO> clientBikeList;
}
