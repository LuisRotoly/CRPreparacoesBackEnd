package com.crpreparacoes.services;

import com.crpreparacoes.dto.ClientBikeDTO;
import com.crpreparacoes.bodyrequestinput.client.CreateClientRequest;
import com.crpreparacoes.bodyrequestinput.client.EditClientRequest;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.Client;
import com.crpreparacoes.models.ClientBike;
import com.crpreparacoes.repositories.ClientBikeRepository;
import com.crpreparacoes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientBikeRepository clientBikeRepository;

    @Autowired
    private ClientBikeService clientBikeService;

    public List<Client> listAllClients() {
        return clientRepository.listAllClients();
    }

    public void addNewClient(CreateClientRequest createClientRequest) {
        if(clientRepository.findByCpfcnpj(createClientRequest.getCpfcnpj()) != null){
            throw new ApiRequestException("Erro! CPF ou CNPJ do cliente já existe!");
        }
        for(ClientBikeDTO clientBike: createClientRequest.getClientBikeList()) {
            if (clientBikeRepository.findByPlate(clientBike.getPlate()) != null) {
                throw new ApiRequestException("Erro! Placa já cadastrada!");
            }
        }
        Client client = new Client();
        client.setName(createClientRequest.getName());
        client.setCpfcnpj(createClientRequest.getCpfcnpj());
        client.setCep(createClientRequest.getCep());
        client.setAddressNumber(createClientRequest.getAddressNumber());
        client.setBirthDate(createClientRequest.getBirthDate());
        client.setPhone(createClientRequest.getPhone());
        client.setOptionalPhone(createClientRequest.getOptionalPhone());
        client.setNotes(createClientRequest.getNotes());
        client.setNickname(createClientRequest.getNickname());
        client.setCreatedAt(LocalDateTime.now());
        try {
            client = clientRepository.save(client);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o cliente!");
        }
        for(ClientBikeDTO clientBike: createClientRequest.getClientBikeList()) {
            clientBikeService.addNewClientBike(client.getId(), clientBike.getBike().getId(), clientBike.getPlate(), clientBike.getYear());
        }
    }

    public void editClientById(EditClientRequest editClientRequest) {
        Client client = clientRepository.findById(editClientRequest.getId()).get();
        client.setName(editClientRequest.getName());
        if(!editClientRequest.getCpfcnpj().equals(client.getCpfcnpj())){
            client.setCpfcnpj(editClientRequest.getCpfcnpj());
        }
        client.setCreatedAt(client.getCreatedAt());
        client.setCep(editClientRequest.getCep());
        client.setAddressNumber(editClientRequest.getAddressNumber());
        client.setBirthDate(editClientRequest.getBirthDate());
        client.setPhone(editClientRequest.getPhone());
        client.setOptionalPhone(editClientRequest.getOptionalPhone());
        client.setNotes(editClientRequest.getNotes());
        client.setNickname(editClientRequest.getNickname());
        client.setUpdatedAt(LocalDateTime.now());
        try {
            clientRepository.save(client);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar o cliente!");
        }
        List<ClientBike> clientBikeList = clientBikeRepository.listAllClientBikeByClientId(client.getId());
        List<ClientBikeDTO> clientBikeDTOList = editClientRequest.getClientBikeList();
        for (ClientBike clientBike: clientBikeList) {
            boolean matchClientBike = false;
            for (ClientBikeDTO clientBikeDTO : clientBikeDTOList) {
                if (clientBike.getPlate().equals(clientBikeDTO.getPlate()) && clientBike.getBike().getId() == clientBikeDTO.getBike().getId()) {
                    matchClientBike = true;
                    clientBikeDTOList.remove(clientBikeDTO);
                    break;
                }
            }
            if(!matchClientBike){
                clientBikeRepository.delete(clientBike);
            }
        }
        for(ClientBikeDTO clientBikeDTO : clientBikeDTOList){
            clientBikeService.addNewClientBike(editClientRequest.getId(), clientBikeDTO.getBike().getId(), clientBikeDTO.getPlate(), clientBikeDTO.getYear());
        }
    }

    public Client listClientById(Long id) {
        return clientRepository.findById(id).get();
    }

    public List<Client> filterListClients(String word) {
        return clientRepository.filterListClients(word);
    }
}
