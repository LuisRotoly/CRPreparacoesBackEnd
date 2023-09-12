package com.crpreparacoes.crpreparacoes.controllers;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.CreateClientRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.EditClientRequest;
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

    /**Método para buscar os clientes usando um filtro
     * @return List<Client> - Lista de clientes
     */
    @RequestMapping(value="/filterListClients", method = RequestMethod.GET)
    public @ResponseBody List<Client> filterListClients(@RequestParam String word){
        return clientService.filterListClients(word);
    }

    /**Método para buscar um cliente
     * @return Client - Cliente
     */
    @RequestMapping(value="/listClientById", method = RequestMethod.GET)
    public @ResponseBody Client listClientById(@RequestParam Long id){
        return clientService.listClientById(id);
    }

    /**Método para adicionar um cliente
     */
    @RequestMapping(value="/addClient", method = RequestMethod.POST)
    public void addClient(@RequestBody CreateClientRequest createClientRequest){
        clientService.addNewClient(createClientRequest);
    }

    /**Método para editar um cliente
     */
    @RequestMapping(value="/editClient", method = RequestMethod.POST)
    public void editClient(@RequestBody EditClientRequest editClientRequest){
        clientService.editClientById(editClientRequest);
    }
}
