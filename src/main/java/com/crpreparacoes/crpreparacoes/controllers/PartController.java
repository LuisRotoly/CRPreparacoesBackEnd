package com.crpreparacoes.crpreparacoes.controllers;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.part.CreatePartRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.part.EditPartRequest;
import com.crpreparacoes.crpreparacoes.models.Bike;
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

    /**Método para buscar as peças usando um filtro
     * @return List<Part> - Lista de peças
     */
    @RequestMapping(value="/filterListParts", method = RequestMethod.GET)
    public @ResponseBody List<Part> filterListParts(@RequestParam String word){
        return partService.filterListParts(word);
    }

    /**Método para buscar uma peça
     * @return Part - Peça
     */
    @RequestMapping(value="/listPartById", method = RequestMethod.GET)
    public @ResponseBody Part listPartById(@RequestParam Long id){
        return partService.listPartById(id);
    }

    /**Método para adicionar uma peça
     */
    @RequestMapping(value="/addPart", method = RequestMethod.POST)
    public void addPart(@RequestBody CreatePartRequest createPartRequest){
        partService.addNewPart(createPartRequest);
    }

    /**Método para editar uma peça
     */
    @RequestMapping(value="/editPart", method = RequestMethod.POST)
    public void editPart(@RequestBody EditPartRequest editPartRequest){
        partService.editPartById(editPartRequest);
    }
}
