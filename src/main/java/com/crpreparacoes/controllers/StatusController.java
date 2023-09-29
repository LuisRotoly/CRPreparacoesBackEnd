package com.crpreparacoes.controllers;

import com.crpreparacoes.models.Status;
import com.crpreparacoes.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StatusController {

    @Autowired
    private StatusService statusService;

    /**Método para buscar todos os status
     * @return List<Status> - Lista de status
     */
    @RequestMapping(value="/listStatus", method = RequestMethod.GET)
    public @ResponseBody List<Status> listStatus(){
        return statusService.listAllStatus();
    }
}
