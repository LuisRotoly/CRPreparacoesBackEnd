package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.CreateClient;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.EditClient;
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

    public void addNewClient(CreateClient createClient) {
        if(clientRepository.findByCpfcnpj(createClient.getCpfcnpj()) != null){
            throw new ApiRequestException("Erro! CPF ou CNPJ do cliente já existe!");
        }
        Client client = new Client();
        client.setName(createClient.getName());
        client.setCpfcnpj(createClient.getCpfcnpj());
        client.setAddress(createClient.getAddress());
        client.setPhone(createClient.getPhone());
        client.setNickname(createClient.getNickname());
        client.setCreatedAt(LocalDateTime.now());
        try {
            clientRepository.save(client);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o cliente!");
        }
    }

    public void editClientById(EditClient editClient) {
        try {
            clientRepository.editClientById(editClient.getId(),editClient.getName(),editClient.getCpfcnpj(),editClient.getPhone(),editClient.getNickname(),editClient.getAddress(),LocalDateTime.now());
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar o cliente!");
        }
    }
}
