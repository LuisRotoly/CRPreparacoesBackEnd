package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.bikeService.CreateBikeServiceRequest;
import com.crpreparacoes.bodyrequestinput.bikeService.EditBikeServiceRequest;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.BikePart;
import com.crpreparacoes.models.BikeService;
import com.crpreparacoes.repositories.BikeServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceService {

    @Autowired
    private BikeServiceRepository bikeServiceRepository;

    public List<BikeService> listAllBikeServices() {
        return bikeServiceRepository.listAllBikeServices();
    }

    public List<BikeService> filterListBikeServices(String word) {
        return bikeServiceRepository.filterListBikeServices(word);
    }

    public BikeService listBikeServiceById(Long id) {
        return bikeServiceRepository.findById(id).get();
    }

    public void addNewBikeService(CreateBikeServiceRequest createBikeServiceRequest) {
        if(bikeServiceRepository.findByName(createBikeServiceRequest.getName())!=null){
            throw new ApiRequestException("Erro! Nome do serviço já existe!");
        }
        BikeService bikeService = new BikeService();
        bikeService.setName(createBikeServiceRequest.getName());
        bikeService.setValue(createBikeServiceRequest.getValue());
        try {
            bikeServiceRepository.save(bikeService);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar um serviço!");
        }
    }

    public void editBikeServiceById(EditBikeServiceRequest editBikeServiceRequest) {
        BikeService bikeService = bikeServiceRepository.findById(editBikeServiceRequest.getId()).get();
        BikeService bikeServiceCompare = bikeServiceRepository.findByName(editBikeServiceRequest.getName());
        if(bikeServiceCompare != null) {
            if (bikeService.getId() != bikeServiceCompare.getId()) {
                throw new ApiRequestException("Erro! Nome do serviço já existe!");
            }
        }
        bikeService.setName(editBikeServiceRequest.getName());
        bikeService.setValue(editBikeServiceRequest.getValue());
        try {
            bikeServiceRepository.save(bikeService);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar um serviço!");
        }
    }

    public void deleteBikeService(Long id) {
        bikeServiceRepository.deleteById(id);
    }
}
