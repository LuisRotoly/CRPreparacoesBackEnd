package com.crpreparacoes.services;

import com.crpreparacoes.models.Bike;
import com.crpreparacoes.repositories.BikePartRelBikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikePartRelBikeService {

    @Autowired
    private BikePartRelBikeRepository bikePartRelBikeRepository;

    public List<Bike> getBikesByBikePartId(Long bikePartId) {
        return bikePartRelBikeRepository.findBikesByBikePartId(bikePartId);
    }
}
