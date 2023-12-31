package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.bike.CreateBikeRequest;
import com.crpreparacoes.bodyrequestinput.bike.EditBikeRequest;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.Bike;
import com.crpreparacoes.repositories.BikeBrandRepository;
import com.crpreparacoes.repositories.BikeRepository;
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
        if(bikeRepository.findBikeExists(createBikeRequest.getName(), createBikeRequest.getBrandId()) != null){
            throw new ApiRequestException("Erro! Moto já criada!");
        }
        Bike bike = new Bike();
        bike.setName(createBikeRequest.getName());
        bike.setBikeBrand(bikeBrandRepository.findById(createBikeRequest.getBrandId()).get());
        bike.setCreatedAt(LocalDateTime.now());
        try {
            bikeRepository.save(bike);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a moto!");
        }
    }

    public void editBikeById(EditBikeRequest editBikeRequest) {
        Bike bike = new Bike();
        bike.setId(editBikeRequest.getId());
        bike.setName(editBikeRequest.getName());
        bike.setBikeBrand(bikeBrandRepository.findById(editBikeRequest.getBrandId()).get());
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
