package com.crpreparacoes.bodyrequestinput.client;

import com.crpreparacoes.dto.ClientBikeDTO;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditClientRequest {
    private Long id;
    private String name;
    private String cpfcnpj;
    private String cep;
    private String street;
    private String addressNumber;
    private String district;
    private String birthDate;
    private String phone;
    private String optionalPhone;
    private String notes;
    private String nickname;
    private List<ClientBikeDTO> clientBikeList;
}
