package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.bikeBrand.CreateBikeBrandRequest;
import com.crpreparacoes.models.BikeBrand;
import com.crpreparacoes.services.BikeBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BikeBrandController {

    @Autowired
    private BikeBrandService bikeBrandService;

    /**Método para buscar todas as marcas de motos
     * @return List<BikeBrand> - Lista de marcas de motos
     */
    @RequestMapping(value="/listBikeBrands", method = RequestMethod.GET)
    public @ResponseBody List<BikeBrand> listBikeBrands(){
        return bikeBrandService.listAllBikeBrands();
    }

    /**Método para adicionar uma marca de moto
     */
    @RequestMapping(value="/addBikeBrand", method = RequestMethod.POST)
    public void addBikeBrand(@RequestBody CreateBikeBrandRequest createBikeBrandRequest){
        bikeBrandService.addNewBikeBrand(createBikeBrandRequest);
    }
}
