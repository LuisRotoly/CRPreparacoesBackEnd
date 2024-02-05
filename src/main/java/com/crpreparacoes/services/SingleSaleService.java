package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.singleSale.CreateSingleSaleRequest;
import com.crpreparacoes.dto.SingleSaleDTO;
import com.crpreparacoes.dto.SingleSaleRelBikePartDTO;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.*;
import com.crpreparacoes.repositories.BikePartRepository;
import com.crpreparacoes.repositories.SingleSaleRelBikePartRepository;
import com.crpreparacoes.repositories.SingleSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SingleSaleService {

    @Autowired
    private SingleSaleRepository singleSaleRepository;

    @Autowired
    private SingleSaleRelBikePartRepository singleSaleRelBikePartRepository;

    @Autowired
    private BikePartRepository bikePartRepository;

    private Integer reduceStockQuantity(Integer oldStockQuantity, Integer selledQuantity){
        return oldStockQuantity - selledQuantity;
    }

    public void addSingleSale(CreateSingleSaleRequest createSingleSaleRequest) {
        SingleSale singleSale = new SingleSale();
        singleSale.setClient(createSingleSaleRequest.getClient());
        singleSale.setCreatedAt(LocalDateTime.now());
        try {
            singleSaleRepository.save(singleSale);
            for (SingleSaleRelBikePart laborOrBikePartSingleSale: createSingleSaleRequest.getSingleSaleRelBikePartList()) {
                BikePart bikePart = laborOrBikePartSingleSale.getBikePart();
                laborOrBikePartSingleSale.setSingleSale(singleSale);
                laborOrBikePartSingleSale.setBikePart(bikePart);
                laborOrBikePartSingleSale.setDefaultValue(bikePart.getValue());
                singleSaleRelBikePartRepository.save(laborOrBikePartSingleSale);
                if(bikePart != null) {
                    bikePart.setStockQuantity(reduceStockQuantity(bikePart.getStockQuantity(),laborOrBikePartSingleSale.getQuantity()));
                    bikePartRepository.save(bikePart);
                }
            }
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a venda avulsa!");
        }
    }

    public List<SingleSaleRelBikePartDTO> getSingleSaleHistoryByBikePartId(Long bikePartId) {
        List<SingleSaleRelBikePart> laborOrBikePartSingleSaleList =
                singleSaleRelBikePartRepository.getSingleSaleByBikePartId(bikePartRepository.findById(bikePartId).get().getId());
        List<SingleSaleRelBikePartDTO> laborOrBikePartSingleSaleDTOList = new ArrayList<>();
        for (SingleSaleRelBikePart laborOrBikePartSingleSale: laborOrBikePartSingleSaleList) {
            SingleSaleRelBikePartDTO laborOrBikePartSingleSaleDTO = new SingleSaleRelBikePartDTO();
            laborOrBikePartSingleSaleDTO.setId(laborOrBikePartSingleSale.getId());
            laborOrBikePartSingleSaleDTO.setClient(laborOrBikePartSingleSale.getSingleSale().getClient());
            laborOrBikePartSingleSaleDTO.setCreatedAt(laborOrBikePartSingleSale.getSingleSale().getCreatedAt());
            laborOrBikePartSingleSaleDTO.setBikePartQuantity(laborOrBikePartSingleSale.getQuantity());
            laborOrBikePartSingleSaleDTOList.add(laborOrBikePartSingleSaleDTO);
        }
        return laborOrBikePartSingleSaleDTOList;
    }

    public SingleSaleDTO getSingleSaleById(Long singleSaleId) {
        SingleSale singleSale = singleSaleRepository.findById(singleSaleId).get();
        List<SingleSaleRelBikePart> singleSaleRelBikePartList = singleSaleRelBikePartRepository.getSingleSaleBySingleSaleId(singleSaleId);
        SingleSaleDTO singleSaleDTO = new SingleSaleDTO();
        singleSaleDTO.setClientName(singleSale.getClient());
        singleSaleDTO.setCreatedAt(singleSale.getCreatedAt());
        singleSaleDTO.setSingleSaleRelBikePartList(singleSaleRelBikePartList);
        double totalValue = 0;
        for (SingleSaleRelBikePart singleSaleRelBikePart: singleSaleRelBikePartList) {
            totalValue = totalValue + (singleSaleRelBikePart.getQuantity() * singleSaleRelBikePart.getValue());
        }
        singleSaleDTO.setTotalValue(totalValue);
        return singleSaleDTO;
    }
}
