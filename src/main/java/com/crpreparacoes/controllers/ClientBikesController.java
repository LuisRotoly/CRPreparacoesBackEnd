package com.crpreparacoes.controllers;

import com.crpreparacoes.dto.BikeDTO;
import com.crpreparacoes.dto.ClientBikeDTO;
import com.crpreparacoes.models.Bike;
import com.crpreparacoes.models.BikePart;
import com.crpreparacoes.services.ClientBikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ClientBikesController {

    @Autowired
    private ClientBikeService clientBikesService;

    /**Método para buscar todas as motos de um cliente
     * @return List<ClientBike> - Lista de clientes
     */
    @RequestMapping(value="/listClientBikeById", method = RequestMethod.GET)
    public @ResponseBody List<ClientBikeDTO> listClientBikeById(@RequestParam Long clientId){
        return clientBikesService.listAllClientBikeByClientId(clientId);
    }

    /**Método para buscar uma moto pela placa
     * @return BikeDTO - Moto
     */
    @RequestMapping(value="/listBikeByPlate", method = RequestMethod.GET)
    public @ResponseBody BikeDTO listBikeByPlate(@RequestParam String plate){
        return clientBikesService.listBikeByPlate(plate);
    }
}
