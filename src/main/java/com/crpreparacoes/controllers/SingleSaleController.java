package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.singleSale.CreateSingleSaleRequest;
import com.crpreparacoes.dto.LaborOrBikePartSingleSaleDTO;
import com.crpreparacoes.services.SingleSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class SingleSaleController {

    @Autowired
    private SingleSaleService singleSaleService;

    /**Método para adicionar uma venda avulsa
     */
    @RequestMapping(value="/addSingleSale", method = RequestMethod.POST)
    public void addSingleSale(@RequestBody CreateSingleSaleRequest createSingleSaleRequest){
        singleSaleService.addSingleSale(createSingleSaleRequest);
    }

    /**Método para buscar as vendas avulsas usando o id da peça da moto
     * @return List<LaborOrBikePartSingleSaleDTO> - Lista de vendas avulsas
     */
    @RequestMapping(value="/getSingleSaleHistoryByBikePartId", method = RequestMethod.GET)
    public @ResponseBody List<LaborOrBikePartSingleSaleDTO> getSingleSaleHistoryByBikePartId(@RequestParam Long bikePartId){
        return singleSaleService.getSingleSaleHistoryByBikePartId(bikePartId);
    }
}
