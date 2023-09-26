package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.bikeService.CreateBikeServiceRequest;
import com.crpreparacoes.bodyrequestinput.bikeService.EditBikeServiceRequest;
import com.crpreparacoes.models.BikeService;
import com.crpreparacoes.services.BikeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BikeServiceController {

    @Autowired
    private BikeServiceService bikeServiceService;

    /**Método para buscar todos os serviços
     * @return List<BikeService> - Lista de serviços
     */
    @RequestMapping(value="/listBikeServices", method = RequestMethod.GET)
    public @ResponseBody List<BikeService> listBikeServices(){
        return bikeServiceService.listAllBikeServices();
    }

    /**Método para buscar os serviços usando um filtro
     * @return List<BikeService> - Lista de serviços
     */
    @RequestMapping(value="/filterListBikeServices", method = RequestMethod.GET)
    public @ResponseBody List<BikeService> filterListBikeServices(@RequestParam String word){
        return bikeServiceService.filterListBikeServices(word);
    }

    /**Método para buscar um serviço
     * @return BikeService - Serviço
     */
    @RequestMapping(value="/listBikeServiceById", method = RequestMethod.GET)
    public @ResponseBody BikeService listBikeServiceById(@RequestParam Long id){
        return bikeServiceService.listBikeServiceById(id);
    }

    /**Método para adicionar um serviço
     */
    @RequestMapping(value="/addBikeService", method = RequestMethod.POST)
    public void addBikeService(@RequestBody CreateBikeServiceRequest createBikeServiceRequest){
        bikeServiceService.addNewBikeService(createBikeServiceRequest);
    }

    /**Método para editar um serviço
     */
    @RequestMapping(value="/editBikeService", method = RequestMethod.POST)
    public void editBikeService(@RequestBody EditBikeServiceRequest editBikeServiceRequest){
        bikeServiceService.editBikeServiceById(editBikeServiceRequest);
    }

    /**Método para deletar um serviço
     */
    @RequestMapping(value="/deleteBikeService", method = RequestMethod.DELETE)
    public void deleteBikeService(@RequestParam Long id){
        bikeServiceService.deleteBikeService(id);
    }
}
