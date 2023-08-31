package com.crpreparacoes.crpreparacoes.controllers;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.part.CreatePart;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.part.EditPart;
import com.crpreparacoes.crpreparacoes.models.Part;
import com.crpreparacoes.crpreparacoes.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PartController {

    @Autowired
    private PartService partService;

    /**Método para buscar todas as peças
     * @return List<Part> - Lista de peças
     */
    @RequestMapping(value="/listParts", method = RequestMethod.GET)
    public @ResponseBody List<Part> listParts(){
        return partService.listAllParts();
    }

    /**Método para adicionar uma peça
     */
    @RequestMapping(value="/addPart", method = RequestMethod.POST)
    public @ResponseBody void addPart(@RequestBody CreatePart createPart){
        partService.addNewPart(createPart);
    }

    /**Método para editar uma peça
     */
    @RequestMapping(value="/editPart", method = RequestMethod.POST)
    public @ResponseBody void editPart(@RequestBody EditPart editPart){
        partService.editPartById(editPart);
    }
}
