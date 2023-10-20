package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.bikePart.EditBikePartStockRequest;
import com.crpreparacoes.dto.BikePartDTO;
import com.crpreparacoes.bodyrequestinput.bikePart.CreateBikePartRequest;
import com.crpreparacoes.bodyrequestinput.bikePart.EditBikePartRequest;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.BikePart;
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

    public List<BikePart> listAllBikeParts() {
        return bikePartRepository.listAllBikeParts();
    }

    public void addNewBikePart(CreateBikePartRequest createBikePartRequest) {
        BikePart bikePart = new BikePart();
        if(bikePartRepository.findByName(createBikePartRequest.getName())!=null){
            throw new ApiRequestException("Erro! Nome da peça já existe!");
        }
        bikePart.setName(createBikePartRequest.getName());
        bikePart.setValue(createBikePartRequest.getValue());
        bikePart.setProfitPercentage(createBikePartRequest.getProfitPercentage());
        bikePart.setStockQuantity(createBikePartRequest.getStockQuantity());
        bikePart.setCreatedAt(LocalDateTime.now());
        try {
            bikePartRepository.save(bikePart);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a peça!");
        }
    }

    public void editBikePartById(EditBikePartRequest editBikePartRequest) {
        BikePart bikePart = bikePartRepository.findById(editBikePartRequest.getId()).get();
        BikePart bikePartCompare = bikePartRepository.findByName(editBikePartRequest.getName());
        if(bikePart.getId() != bikePartCompare.getId()){
            throw new ApiRequestException("Erro! Nome da peça já existe!");
        }
        bikePart.setName(editBikePartRequest.getName());
        bikePart.setValue(editBikePartRequest.getValue());
        bikePart.setProfitPercentage(editBikePartRequest.getProfitPercentage());
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

    public BikePartDTO listBikePartById(Long id) {
        BikePart bikePart = bikePartRepository.findById(id).get();
        BikePartDTO bikePartDTO = new BikePartDTO();
        bikePartDTO.setName(bikePart.getName());
        bikePartDTO.setValue(bikePart.getValue());
        bikePartDTO.setProfitPercentage(bikePart.getProfitPercentage());
        bikePartDTO.setStockQuantity(bikePart.getStockQuantity());
        bikePartDTO.setFinalValue(bikePart.getValue() + (bikePart.getValue()*bikePart.getProfitPercentage())/100);
        return bikePartDTO;
    }

    public void editBikePartStockById(EditBikePartStockRequest editBikePartStockRequest) {
        BikePart bikePart = bikePartRepository.findById(editBikePartStockRequest.getId()).get();
        bikePart.setStockQuantity(editBikePartStockRequest.getStockQuantity());
        bikePart.setUpdatedAt(LocalDateTime.now());
        try {
            bikePartRepository.save(bikePart);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar o estoque da peça!");
        }
    }
}
