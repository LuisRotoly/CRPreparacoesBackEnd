package com.crpreparacoes.crpreparacoes.controllers;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBikes.CreateClientBikes;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.clientBikes.EditClientBikes;
import com.crpreparacoes.crpreparacoes.models.ClientBikes;
import com.crpreparacoes.crpreparacoes.services.ClientBikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ClientBikesController {

    @Autowired
    private ClientBikesService clientBikesService;

    /**Método para buscar todas as motos de um cliente
     * @return List<ClientBikes> - Lista de clientes
     */
    @RequestMapping(value="/listClientBikes", method = RequestMethod.GET)
    public @ResponseBody List<ClientBikes> listClientBikes(@RequestParam Long clientId){
        return clientBikesService.listAllClientBikes(clientId);
    }

    /**Método para adicionar uma moto a um cliente
     */
    @RequestMapping(value="/addClientBikes", method = RequestMethod.POST)
    public @ResponseBody void addClientBikes(@RequestBody CreateClientBikes createClientBikes){
        clientBikesService.addNewClientBikes(createClientBikes);
    }

    /**Método para editar uma moto de um cliente
     */
    @RequestMapping(value="/editClientBikes", method = RequestMethod.POST)
    public @ResponseBody void editClientBikes(@RequestBody EditClientBikes editClientBikes){
        clientBikesService.editClientBikesById(editClientBikes);
    }
}
