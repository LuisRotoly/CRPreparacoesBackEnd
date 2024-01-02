package com.crpreparacoes.controllers;

import com.crpreparacoes.bodyrequestinput.singleSale.CreateSingleSaleRequest;
import com.crpreparacoes.dto.SingleSaleDTO;
import com.crpreparacoes.dto.SingleSaleRelBikePartDTO;
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

    /**Método para buscar uma venda avulsa
     * @return SingleSaleDTO - Venda avulsa
     */
    @RequestMapping(value="/getSingleSaleById", method = RequestMethod.GET)
    public @ResponseBody SingleSaleDTO getSingleSaleById(@RequestParam Long singleSaleId){
        return singleSaleService.getSingleSaleById(singleSaleId);
    }

    /**Método para buscar as vendas avulsas usando o id da peça da moto
     * @return List<SingleSaleRelBikePartDTO> - Lista de vendas avulsas
     */
    @RequestMapping(value="/getSingleSaleHistoryByBikePartId", method = RequestMethod.GET)
    public @ResponseBody List<SingleSaleRelBikePartDTO> getSingleSaleHistoryByBikePartId(@RequestParam Long bikePartId){
        return singleSaleService.getSingleSaleHistoryByBikePartId(bikePartId);
    }
}
