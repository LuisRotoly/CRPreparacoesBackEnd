package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.bikeBrand.CreateBikeBrandRequest;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.BikeBrand;
import com.crpreparacoes.crpreparacoes.repositories.BikeBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeBrandService {

    @Autowired
    private BikeBrandRepository bikeBrandRepository;

    public List<BikeBrand> listAllBikeBrands() {
        return bikeBrandRepository.listAllBikeBrands();
    }

    public void addNewBikeBrand(CreateBikeBrandRequest createBikeBrandRequest) {
        BikeBrand bikeBrand = new BikeBrand();
        bikeBrand.setName(createBikeBrandRequest.getName());
        try {
            bikeBrandRepository.save(bikeBrand);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a marca da moto!");
        }
    }
}
