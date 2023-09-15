package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.DTO.ClientBikeDTO;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.CreateClientRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.EditClientRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBike.CreateClientBikeRequest;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.Client;
import com.crpreparacoes.crpreparacoes.models.ClientBike;
import com.crpreparacoes.crpreparacoes.repositories.ClientBikeRepository;
import com.crpreparacoes.crpreparacoes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        /*
        Faço a verificação primeiro e altero o método addNewClientBike do ClientBikeService ou deixo do jeito que está(se for do jeito que está eu posso até arrumar os parametros pra não ficar essa loucura que está)?

        for(ClientBikeDTO clientBike: createClientRequest.getClientBikeList()) {
            if (clientBikeRepository.findByPlate(clientBike.getPlate()) != null) {
                throw new ApiRequestException("Erro! Placa já cadastrada!");
            }
        }
        */
        Client client = new Client();
        client.setName(createClientRequest.getName());
        client.setCpfcnpj(createClientRequest.getCpfcnpj());
        client.setAddress(createClientRequest.getAddress());
        client.setPhone(createClientRequest.getPhone());
        client.setNickname(createClientRequest.getNickname());
        client.setCreatedAt(LocalDateTime.now());
        try {
            client = clientRepository.save(client);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o cliente!");
        }
        for(ClientBikeDTO clientBike: createClientRequest.getClientBikeList()) {
            CreateClientBikeRequest createClientBikeRequest = new CreateClientBikeRequest();
            createClientBikeRequest.setClientId(Math.toIntExact(client.getId()));
            createClientBikeRequest.setBikeId(Math.toIntExact(clientBike.getBike().getId()));
            createClientBikeRequest.setPlate(clientBike.getPlate());
            clientBikeService.addNewClientBike(createClientBikeRequest);
        }
    }

    public void editClientById(EditClientRequest editClientRequest) {
        Client client = clientRepository.findById(Math.toIntExact(editClientRequest.getId())).get();
        client.setName(editClientRequest.getName());
        if(!editClientRequest.getCpfcnpj().equals(client.getCpfcnpj())){
            client.setCpfcnpj(editClientRequest.getCpfcnpj());
        }
        client.setAddress(editClientRequest.getAddress());
        client.setPhone(editClientRequest.getPhone());
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
            boolean test = false;
            for (ClientBikeDTO clientBikeDTO : clientBikeDTOList) {
                if (clientBike.getPlate().equals(clientBikeDTO.getPlate()) && clientBike.getBike().getId() == clientBikeDTO.getBike().getId()) {
                    test = true;
                    clientBikeDTOList.remove(clientBikeDTO);
                    break;
                }
            }
            if(!test){
                clientBikeRepository.delete(clientBike);
            }
        }
        for(ClientBikeDTO clientBikeDTO : clientBikeDTOList){
            CreateClientBikeRequest createClientBikeRequest = new CreateClientBikeRequest();
            createClientBikeRequest.setClientId(Math.toIntExact(editClientRequest.getId()));
            createClientBikeRequest.setBikeId(Math.toIntExact(clientBikeDTO.getBike().getId()));
            createClientBikeRequest.setPlate(clientBikeDTO.getPlate());
            clientBikeService.addNewClientBike(createClientBikeRequest);
        }
    }

    public Client listClientById(Long id) {
        return clientRepository.findById(Math.toIntExact(id)).get();
    }

    public List<Client> filterListClients(String word) {
        return clientRepository.filterListClients(word);
    }
}
