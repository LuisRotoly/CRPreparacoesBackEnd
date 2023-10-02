package com.crpreparacoes.controllers;

import com.crpreparacoes.models.Bike;
import com.crpreparacoes.services.BikePartRelBikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BikePartRelBikeController {

    @Autowired
    private BikePartRelBikeService bikePartRelBikeService;

    /**Método para buscar todas as motos relacionadas a peça
     * @return List<Bike> - Lista de motos
     */
    @RequestMapping(value="/getBikesByBikePartId", method = RequestMethod.GET)
    public @ResponseBody List<Bike> getBikesByBikePartId(Long bikePartId){
        return bikePartRelBikeService.getBikesByBikePartId(bikePartId);
    }
}
