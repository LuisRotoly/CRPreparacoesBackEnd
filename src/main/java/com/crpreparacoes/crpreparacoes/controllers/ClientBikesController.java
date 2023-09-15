package com.crpreparacoes.crpreparacoes.controllers;

import com.crpreparacoes.crpreparacoes.DTO.ClientBikeDTO;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBike.CreateClientBikeRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBike.EditClientBikeRequest;
import com.crpreparacoes.crpreparacoes.models.ClientBike;
import com.crpreparacoes.crpreparacoes.services.ClientBikeService;
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

    /**Método para adicionar uma moto a um cliente
     */
    @RequestMapping(value="/addClientBike", method = RequestMethod.POST)
    public void addClientBike(@RequestBody CreateClientBikeRequest createClientBikeRequest){
        clientBikesService.addNewClientBike(createClientBikeRequest);
    }

    /**Método para editar uma moto de um cliente
     */
    @RequestMapping(value="/editClientBike", method = RequestMethod.POST)
    public void editClientBike(@RequestBody EditClientBikeRequest editClientBikeRequest){
        clientBikesService.editClientBikeById(editClientBikeRequest);
    }
}
