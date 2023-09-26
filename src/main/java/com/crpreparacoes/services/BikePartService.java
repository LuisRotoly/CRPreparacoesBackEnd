package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.bikePart.CreateBikePartRequest;
import com.crpreparacoes.bodyrequestinput.bikePart.EditBikePartRequest;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.BikePart;
import com.crpreparacoes.repositories.BikeRepository;
import com.crpreparacoes.repositories.BikePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BikePartService {

    @Autowired
    private BikePartRepository bikePartRepository;

    @Autowired
    private BikeRepository bikeRepository;

    public List<BikePart> listAllBikeParts() {
        return bikePartRepository.listAllBikeParts();
    }

    public void addNewBikePart(CreateBikePartRequest createBikePartRequest) {
        if(bikePartRepository.findByNameAndBike(createBikePartRequest.getName(), createBikePartRequest.getBikeId()) != null){
            throw new ApiRequestException("Erro! A peça já existe!");
        }
        BikePart bikePart = new BikePart();
        bikePart.setName(createBikePartRequest.getName());
        bikePart.setValue(createBikePartRequest.getValue());
        bikePart.setStockQuantity(createBikePartRequest.getStockQuantity());
        bikePart.setBike(bikeRepository.findById(createBikePartRequest.getBikeId()).get());
        bikePart.setCreatedAt(LocalDateTime.now());
        try {
            bikePartRepository.save(bikePart);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a peça!");
        }
    }

    public void editBikePartById(EditBikePartRequest editBikePartRequest) {
        BikePart bikePart = new BikePart();
        bikePart.setId(editBikePartRequest.getId());
        bikePart.setName(editBikePartRequest.getName());
        bikePart.setValue(editBikePartRequest.getValue());
        bikePart.setStockQuantity(editBikePartRequest.getStockQuantity());
        bikePart.setBike(bikeRepository.findById(editBikePartRequest.getBikeId()).get());
        bikePart.setUpdatedAt(LocalDateTime.now());
        try {
            bikePartRepository.save(bikePart);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar a peça!");
        }
    }

    public List<BikePart> filterListBikeParts(String word) {
        return bikePartRepository.filterListBikes(word);
    }

    public BikePart listBikePartById(Long id) {
        return bikePartRepository.findById(id).get();
    }
}