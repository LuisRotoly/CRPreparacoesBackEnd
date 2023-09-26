package com.crpreparacoes.controllers;

import com.crpreparacoes.DTO.ClientBikeDTO;
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
}
