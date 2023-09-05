package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.part.CreatePartRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.part.EditPartRequest;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.Part;
import com.crpreparacoes.crpreparacoes.repositories.BikeRepository;
import com.crpreparacoes.crpreparacoes.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private BikeRepository bikeRepository;

    public List<Part> listAllParts() {
        return partRepository.listAllParts();
    }

    public void addNewPart(CreatePartRequest createPartRequest) {
        if(partRepository.findByNameAndBike(createPartRequest.getName(), createPartRequest.getBikeId()) != null){
            throw new ApiRequestException("Erro! A peça já existe!");
        }
        Part part = new Part();
        part.setName(createPartRequest.getName());
        part.setValue(createPartRequest.getValue());
        part.setStockQuantity(createPartRequest.getStockQuantity());
        part.setBike(bikeRepository.findById(createPartRequest.getBikeId()).get());
        part.setCreatedAt(LocalDateTime.now());
        try {
            partRepository.save(part);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a peça!");
        }
    }

    public void editPartById(EditPartRequest editPartRequest) {
        Part part = new Part();
        part.setId(editPartRequest.getId());
        part.setName(editPartRequest.getName());
        part.setValue(editPartRequest.getValue());
        part.setStockQuantity(editPartRequest.getStockQuantity());
        part.setBike(bikeRepository.findById(editPartRequest.getBikeId()).get());
        part.setUpdatedAt(LocalDateTime.now());
        try {
            partRepository.save(part);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar a peça!");
        }
    }

    public List<Part> filterListParts(String word) {
        return partRepository.filterListBikes(word);
    }

    public Part listPartById(Long id) {
        return partRepository.findById(Math.toIntExact(id)).get();
    }
}
