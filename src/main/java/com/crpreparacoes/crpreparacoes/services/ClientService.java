package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.CreateClientRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.EditClientRequest;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.Client;
import com.crpreparacoes.crpreparacoes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> listAllClients() {
        return clientRepository.listAllClients();
    }

    public void addNewClient(CreateClientRequest createClientRequest) {
        if(clientRepository.findByCpfcnpj(createClientRequest.getCpfcnpj()) != null){
            throw new ApiRequestException("Erro! CPF ou CNPJ do cliente já existe!");
        }
        Client client = new Client();
        client.setName(createClientRequest.getName());
        client.setCpfcnpj(createClientRequest.getCpfcnpj());
        client.setAddress(createClientRequest.getAddress());
        client.setPhone(createClientRequest.getPhone());
        client.setNickname(createClientRequest.getNickname());
        client.setCreatedAt(LocalDateTime.now());
        try {
            clientRepository.save(client);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o cliente!");
        }
    }

    public void editClientById(EditClientRequest editClientRequest) {
        Client client = new Client();
        client.setId(editClientRequest.getId());
        client.setName(editClientRequest.getName());
        client.setCpfcnpj(editClientRequest.getCpfcnpj());
        client.setAddress(editClientRequest.getAddress());
        client.setPhone(editClientRequest.getPhone());
        client.setNickname(editClientRequest.getNickname());
        client.setUpdatedAt(LocalDateTime.now());
        try {
            clientRepository.save(client);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar o cliente!");
        }
    }

    public Client listClientById(Long id) {
        return clientRepository.findById(Math.toIntExact(id)).get();
    }

    public List<Client> filterListClients(String word) {
        return clientRepository.filterListClients(word);
    }
}
