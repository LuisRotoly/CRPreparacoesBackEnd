package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.part.CreatePart;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.part.EditPart;
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

    public void addNewPart(CreatePart createPart) {
        if(partRepository.findByNameAndBike(createPart.getName(), createPart.getBikeId()) != null){
            throw new ApiRequestException("Erro! A peça já existe!");
        }
        Part part = new Part();
        part.setName(createPart.getName());
        part.setValue(createPart.getValue());
        part.setStockQuantity(createPart.getStockQuantity());
        part.setBike(bikeRepository.findById(createPart.getBikeId()).get());
        part.setCreatedAt(LocalDateTime.now());
        try {
            partRepository.save(part);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a peça!");
        }
    }

    public void editPartById(EditPart editPart) {
        try {
            partRepository.editPartById(editPart.getId(), editPart.getName(), editPart.getValue(), editPart.getStockQuantity(), editPart.getBikeId(), LocalDateTime.now());
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar a peça!");
        }
    }
}
