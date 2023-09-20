package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.bike.CreateBikeRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.bike.EditBikeRequest;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.Bike;
import com.crpreparacoes.crpreparacoes.repositories.BikeBrandRepository;
import com.crpreparacoes.crpreparacoes.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private BikeBrandRepository bikeBrandRepository;

    public List<Bike> listAllBikes() {
        return bikeRepository.listAllBikes();
    }

    public void addNewBike(CreateBikeRequest createBikeRequest) {
        if(bikeRepository.findBikeExists(createBikeRequest.getName(), createBikeRequest.getBrandId(), createBikeRequest.getYear(), createBikeRequest.getEngineCapacity()) != null){
            throw new ApiRequestException("Erro! Moto já criada!");
        }
        Bike bike = new Bike();
        bike.setName(createBikeRequest.getName());
        bike.setBikeBrand(bikeBrandRepository.findById(createBikeRequest.getBrandId()).get());
        bike.setEngineCapacity(createBikeRequest.getEngineCapacity());
        bike.setYear(createBikeRequest.getYear());
        bike.setCreatedAt(LocalDateTime.now());
        try {
            bikeRepository.save(bike);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a moto!");
        }
    }

    public void editBikeById(EditBikeRequest editBikeRequest) {
        Bike bike = new Bike();
        bike.setName(editBikeRequest.getName());
        bike.setBikeBrand(bikeBrandRepository.findById(editBikeRequest.getBrandId()).get());
        bike.setEngineCapacity(editBikeRequest.getEngineCapacity());
        bike.setYear(editBikeRequest.getYear());
        bike.setUpdatedAt(LocalDateTime.now());
        try {
            bikeRepository.save(bike);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar a moto!");
        }
    }

    public List<Bike> filterListBikes(String word) {
        return bikeRepository.filterListBikes(word);
    }

    public Bike listBikeById(Long id) {
        return bikeRepository.findById(id).get();
    }
}
