package com.crpreparacoes.crpreparacoes.controllers;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.bikePart.CreateBikePartRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.bikePart.EditBikePartRequest;
import com.crpreparacoes.crpreparacoes.models.BikePart;
import com.crpreparacoes.crpreparacoes.services.BikePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BikePartController {

    @Autowired
    private BikePartService bikePartService;

    /**Método para buscar todas as peças
     * @return List<BikePart> - Lista de peças
     */
    @RequestMapping(value="/listBikeParts", method = RequestMethod.GET)
    public @ResponseBody List<BikePart> listBikeParts(){
        return bikePartService.listAllBikeParts();
    }

    /**Método para buscar as peças usando um filtro
     * @return List<Part> - Lista de peças
     */
    @RequestMapping(value="/filterListBikeParts", method = RequestMethod.GET)
    public @ResponseBody List<BikePart> filterListBikeParts(@RequestParam String word){
        return bikePartService.filterListBikeParts(word);
    }

    /**Método para buscar uma peça
     * @return BikePart - Peça
     */
    @RequestMapping(value="/listBikePartById", method = RequestMethod.GET)
    public @ResponseBody BikePart listBikePartById(@RequestParam Long id){
        return bikePartService.listBikePartById(id);
    }

    /**Método para adicionar uma peça
     */
    @RequestMapping(value="/addBikePart", method = RequestMethod.POST)
    public void addBikePart(@RequestBody CreateBikePartRequest createBikePartRequest){
        bikePartService.addNewBikePart(createBikePartRequest);
    }

    /**Método para editar uma peça
     */
    @RequestMapping(value="/editBikePart", method = RequestMethod.POST)
    public void editBikePart(@RequestBody EditBikePartRequest editBikePartRequest){
        bikePartService.editBikePartById(editBikePartRequest);
    }
}
