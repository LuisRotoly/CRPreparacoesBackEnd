package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.bike.CreateBikeRequest;
import com.crpreparacoes.bodyrequestinput.bike.EditBikeRequest;
import com.crpreparacoes.models.Bike;
import com.crpreparacoes.services.BikeService;
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
    public @ResponseBody List<Bike> listBikes(){
        return bikeService.listAllBikes();
    }

    /**Método para buscar as motos usando um filtro
     * @return List<Bike> - Lista de motos
     */
    @RequestMapping(value="/filterListBikes", method = RequestMethod.GET)
    public @ResponseBody List<Bike> filterListBikes(@RequestParam String word){
        return bikeService.filterListBikes(word);
    }

    /**Método para buscar uma moto
     * @return Bike - Moto
     */
    @RequestMapping(value="/listBikeById", method = RequestMethod.GET)
    public @ResponseBody Bike listBikeById(@RequestParam Long id){
        return bikeService.listBikeById(id);
    }

    /**Método para adicionar uma moto
     */
    @RequestMapping(value="/addBike", method = RequestMethod.POST)
    public void addBike(@RequestBody CreateBikeRequest createBikeRequest){
        bikeService.addNewBike(createBikeRequest);
    }

    /**Método para editar uma moto
     */
    @RequestMapping(value="/editBike", method = RequestMethod.POST)
    public void editBike(@RequestBody EditBikeRequest editBikeRequest){
        bikeService.editBikeById(editBikeRequest);
    }
}
