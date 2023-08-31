package com.crpreparacoes.crpreparacoes.controllers;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.CreateClient;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.EditClient;
import com.crpreparacoes.crpreparacoes.models.Client;
import com.crpreparacoes.crpreparacoes.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**Método para buscar todos os clientes
     * @return List<Client> - Lista de clientes
     */
    @RequestMapping(value="/listClients", method = RequestMethod.GET)
    public @ResponseBody List<Client> listClients(){
        return clientService.listAllClients();
    }

    /**Método para adicionar um cliente
     */
    @RequestMapping(value="/addClient", method = RequestMethod.POST)
    public @ResponseBody void addClient(@RequestBody CreateClient createClient){
        clientService.addNewClient(createClient);
    }

    /**Método para editar um cliente
     */
    @RequestMapping(value="/editClient", method = RequestMethod.POST)
    public @ResponseBody void editClient(@RequestBody EditClient editClient){
        clientService.editClientById(editClient);
    }
}
