package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBikes.CreateClientBikes;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBikes.EditClientBikes;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.ClientBikes;
import com.crpreparacoes.crpreparacoes.repositories.BikeRepository;
import com.crpreparacoes.crpreparacoes.repositories.ClientBikesRepository;
import com.crpreparacoes.crpreparacoes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientBikesService {

    @Autowired
    private ClientBikesRepository clientBikesRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @ Autowired
    private ClientRepository clientRepository;

    public List<ClientBikes> listAllClientBikes(Long clientId) {
        return clientBikesRepository.listAllClientBikes(clientId);
    }

    public void addNewClientBikes(CreateClientBikes createClientBikes) {
        if(clientBikesRepository.findByPlate(createClientBikes.getPlate()) != null){
            throw new ApiRequestException("Erro! Essa moto do cliente já existe!");
        }
        ClientBikes clientBikes = new ClientBikes();
        clientBikes.setClient(clientRepository.findById(createClientBikes.getClientId()).get());
        clientBikes.setBike(bikeRepository.findById(createClientBikes.getBikeId()).get());
        clientBikes.setPlate(createClientBikes.getPlate());
        clientBikes.setCreatedAt(LocalDateTime.now());
        try {
            clientBikesRepository.save(clientBikes);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar uma moto do cliente!");
        }
    }

    public void editClientBikesById(EditClientBikes editClientBikes) {
        try {
            clientBikesRepository.editClientBikesById(editClientBikes.getId(), editClientBikes.getClientId(), editClientBikes.getBikeId(), editClientBikes.getPlate(), LocalDateTime.now());
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar uma moto do cliente!");
        }
    }
}
