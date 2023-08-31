package com.crpreparacoes.crpreparacoes.controllers;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.bike.CreateBike;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.bike.EditBike;
import com.crpreparacoes.crpreparacoes.models.Client;
import com.crpreparacoes.crpreparacoes.services.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BikeController {

    @Autowired
    private BikeService bikeService;

    /**Método para buscar todas as motos
     * @return List<Bike> - Lista de motos
     */
    @RequestMapping(value="/listBikes", method = RequestMethod.GET)
    public @ResponseBody List<Client> listBikes(){
        return bikeService.listAllBikes();
    }

    /**Método para adicionar uma moto
     */
    @RequestMapping(value="/addBike", method = RequestMethod.POST)
    public @ResponseBody void addBike(@RequestBody CreateBike createBike){
        bikeService.addNewBike(createBike);
    }

    /**Método para editar uma moto
     */
    @RequestMapping(value="/editBike", method = RequestMethod.POST)
    public @ResponseBody void editBike(@RequestBody EditBike editBike){
        bikeService.editBikeById(editBike);
    }
}
