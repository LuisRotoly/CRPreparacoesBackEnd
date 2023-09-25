package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.bikeService.CreateBikeServiceRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.bikeService.EditBikeServiceRequest;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.BikeService;
import com.crpreparacoes.crpreparacoes.repositories.BikeServiceRepository;
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
        BikeService bikeService = new BikeService();
        bikeService.setId(editBikeServiceRequest.getId());
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
