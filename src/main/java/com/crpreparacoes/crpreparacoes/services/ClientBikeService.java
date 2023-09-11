package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBike.CreateClientBikeRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBike.EditClientBikeRequest;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.ClientBike;
import com.crpreparacoes.crpreparacoes.repositories.BikeRepository;
import com.crpreparacoes.crpreparacoes.repositories.ClientBikeRepository;
import com.crpreparacoes.crpreparacoes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientBikeService {

    @Autowired
    private ClientBikeRepository clientBikeRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @ Autowired
    private ClientRepository clientRepository;

    public List<ClientBike> listAllClientBikeByClientId(Long clientId) {
        return clientBikeRepository.listAllClientBikeByClientId(clientId);
    }

    public void addNewClientBike(CreateClientBikeRequest createClientBikeRequest) {
        if(clientBikeRepository.findByPlate(createClientBikeRequest.getPlate()) != null){
            throw new ApiRequestException("Erro! Essa moto do cliente já existe!");
        }
        ClientBike clientBike = new ClientBike();
        clientBike.setClient(clientRepository.findById(createClientBikeRequest.getClientId()).get());
        clientBike.setBike(bikeRepository.findById(createClientBikeRequest.getBikeId()).get());
        clientBike.setPlate(createClientBikeRequest.getPlate());
        clientBike.setCreatedAt(LocalDateTime.now());
        try {
            clientBikeRepository.save(clientBike);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar uma moto do cliente!");
        }
    }

    public void editClientBikeById(EditClientBikeRequest editClientBikeRequest) {
        ClientBike clientBike = new ClientBike();
        clientBike.setId(editClientBikeRequest.getId());
        clientBike.setClient(clientRepository.findById(editClientBikeRequest.getClientId()).get());
        clientBike.setBike(bikeRepository.findById(editClientBikeRequest.getBikeId()).get());
        clientBike.setPlate(editClientBikeRequest.getPlate());
        clientBike.setUpdatedAt(LocalDateTime.now());
        try {
            clientBikeRepository.save(clientBike);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar uma moto do cliente!");
        }
    }
}
