package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.DTO.ClientBikeDTO;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.ClientBike;
import com.crpreparacoes.crpreparacoes.repositories.BikeRepository;
import com.crpreparacoes.crpreparacoes.repositories.ClientBikeRepository;
import com.crpreparacoes.crpreparacoes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientBikeService {

    @Autowired
    private ClientBikeRepository clientBikeRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @ Autowired
    private ClientRepository clientRepository;

    public List<ClientBikeDTO> listAllClientBikeByClientId(Long clientId) {
        List<ClientBike> clientBikeList = clientBikeRepository.listAllClientBikeByClientId(clientId);
        List<ClientBikeDTO> clientBikeDTOList = new ArrayList<>();
        for (ClientBike clientBike: clientBikeList) {
            ClientBikeDTO clientBikeDTO = new ClientBikeDTO(clientBike.getPlate(),clientBike.getBike());
            clientBikeDTOList.add(clientBikeDTO);
        }
        return clientBikeDTOList;
    }

    public void addNewClientBike(Long clienteId, Long bikeId, String plate) {
        ClientBike clientBike = new ClientBike();
        clientBike.setClient(clientRepository.findById(clienteId).get());
        clientBike.setBike(bikeRepository.findById(bikeId).get());
        clientBike.setPlate(plate);
        clientBike.setCreatedAt(LocalDateTime.now());
        try {
            clientBikeRepository.save(clientBike);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar uma moto do cliente!");
        }
    }
}
