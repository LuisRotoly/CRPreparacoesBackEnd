package com.crpreparacoes.services;

import com.crpreparacoes.dto.BikePartDTO;
import com.crpreparacoes.bodyrequestinput.bikePart.CreateBikePartRequest;
import com.crpreparacoes.bodyrequestinput.bikePart.EditBikePartRequest;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.Bike;
import com.crpreparacoes.models.BikePart;
import com.crpreparacoes.models.BikePartRelBike;
import com.crpreparacoes.models.ClientBike;
import com.crpreparacoes.repositories.BikePartRelBikeRepository;
import com.crpreparacoes.repositories.BikeRepository;
import com.crpreparacoes.repositories.BikePartRepository;
import com.crpreparacoes.repositories.ClientBikeRepository;
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

    @Autowired
    private ClientBikeRepository clientBikeRepository;

    @Autowired
    private BikePartRelBikeRepository bikePartRelBikeRepository;

    public List<BikePart> listAllBikeParts() {
        return bikePartRepository.listAllBikeParts();
    }

    public void addNewBikePart(CreateBikePartRequest createBikePartRequest) {
        BikePart bikePart = new BikePart();
        bikePart.setName(createBikePartRequest.getName());
        bikePart.setValue(createBikePartRequest.getValue());
        bikePart.setProfitPercentage(createBikePartRequest.getProfitPercentage());
        bikePart.setStockQuantity(createBikePartRequest.getStockQuantity());
        bikePart.setCreatedAt(LocalDateTime.now());
        try {
            bikePartRepository.save(bikePart);
            for (Bike bike:createBikePartRequest.getBikeList()) {
                BikePartRelBike bikePartRelBike = new BikePartRelBike();
                bikePartRelBike.setBike(bike);
                bikePartRelBike.setBikePart(bikePart);
                bikePartRelBikeRepository.save(bikePartRelBike);
            }
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a peça!");
        }
    }

    public void editBikePartById(EditBikePartRequest editBikePartRequest) {
        BikePart bikePart = new BikePart();
        bikePart.setId(editBikePartRequest.getId());
        bikePart.setName(editBikePartRequest.getName());
        bikePart.setValue(editBikePartRequest.getValue());
        bikePart.setProfitPercentage(editBikePartRequest.getProfitPercentage());
        bikePart.setStockQuantity(editBikePartRequest.getStockQuantity());
        bikePart.setUpdatedAt(LocalDateTime.now());
        try {
            bikePartRepository.save(bikePart);
            bikePartRelBikeRepository.deleteAllByBikePartId(editBikePartRequest.getId());
            for (Bike bike:editBikePartRequest.getBikeList()) {
                BikePartRelBike bikePartRelBike = new BikePartRelBike();
                bikePartRelBike.setBike(bike);
                bikePartRelBike.setBikePart(bikePart);
                bikePartRelBikeRepository.save(bikePartRelBike);
            }
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar a peça!");
        }
    }

    public List<BikePart> filterListBikeParts(String word) {
        return bikePartRelBikeRepository.filterListBikes(word);
    }

    public BikePartDTO listBikePartById(Long id) {
        BikePart bikePart = bikePartRepository.findById(id).get();
        BikePartDTO bikePartDTO = new BikePartDTO();
        bikePartDTO.setName(bikePart.getName());
        bikePartDTO.setValue(bikePart.getValue());
        bikePartDTO.setProfitPercentage(bikePart.getProfitPercentage());
        bikePartDTO.setStockQuantity(bikePart.getStockQuantity());
        bikePartDTO.setFinalValue(bikePart.getValue() + (bikePart.getValue()*bikePart.getProfitPercentage())/100);
        bikePartDTO.setBikeList(bikePartRelBikeRepository.findBikesByBikePartId(id));
        return bikePartDTO;
    }

    public List<BikePart> listBikePartByPlate(String plate) {
        ClientBike clientBike = clientBikeRepository.findByPlate(plate);
        return bikePartRelBikeRepository.listBikePartByBikeId(clientBike.getBike().getId());
    }
}
