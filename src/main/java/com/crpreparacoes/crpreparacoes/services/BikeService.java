package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.bike.CreateBike;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.bike.EditBike;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.Bike;
import com.crpreparacoes.crpreparacoes.models.Client;
import com.crpreparacoes.crpreparacoes.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<Client> listAllBikes() {
        return bikeRepository.listAllBikes();
    }

    public void addNewBike(CreateBike createBike) {
        if(bikeRepository.findBikeExists(createBike.getName(), createBike.getBrand(), createBike.getYear(), createBike.getEngineCapacity()) != null){
            throw new ApiRequestException("Erro! Moto já criada!");
        }
        Bike bike = new Bike();
        bike.setName(createBike.getName());
        bike.setBrand(createBike.getBrand());
        bike.setEngineCapacity(createBike.getEngineCapacity());
        bike.setYear(createBike.getYear());
        bike.setCreatedAt(LocalDateTime.now());
        try {
            bikeRepository.save(bike);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a moto!");
        }
    }

    public void editBikeById(EditBike editBike) {
        try {
            bikeRepository.editBikeById(editBike.getId(),editBike.getName(),editBike.getBrand(),editBike.getEngineCapacity(),editBike.getYear(),LocalDateTime.now());
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar a moto!");
        }
    }
}
