package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.singleSale.CreateSingleSaleRequest;
import com.crpreparacoes.dto.LaborOrBikePartSingleSaleDTO;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.*;
import com.crpreparacoes.repositories.BikePartRepository;
import com.crpreparacoes.repositories.LaborOrBikePartSingleSaleRepository;
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
    private LaborOrBikePartSingleSaleRepository laborOrBikePartSingleSaleRepository;

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
            for (LaborOrBikePartSingleSale laborOrBikePartSingleSale: createSingleSaleRequest.getLaborOrBikePartList()) {
                laborOrBikePartSingleSale.setSingleSale(singleSale);
                laborOrBikePartSingleSaleRepository.save(laborOrBikePartSingleSale);
                BikePart bikePart = bikePartRepository.findByName(laborOrBikePartSingleSale.getName());
                if(bikePart != null) {
                    bikePart.setStockQuantity(reduceStockQuantity(bikePart.getStockQuantity(),laborOrBikePartSingleSale.getQuantity()));
                    bikePartRepository.save(bikePart);
                }
            }
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar a venda avulsa!");
        }
    }

    public List<LaborOrBikePartSingleSaleDTO> getSingleSaleHistoryByBikePartId(Long bikePartId) {
        List<LaborOrBikePartSingleSale> laborOrBikePartSingleSaleList =
                laborOrBikePartSingleSaleRepository.getLaborOrBikePartBudgetByBikePartId(bikePartRepository.findById(bikePartId).get().getName());
        List<LaborOrBikePartSingleSaleDTO> laborOrBikePartSingleSaleDTOList = new ArrayList<>();
        for (LaborOrBikePartSingleSale laborOrBikePartSingleSale: laborOrBikePartSingleSaleList) {
            LaborOrBikePartSingleSaleDTO laborOrBikePartSingleSaleDTO = new LaborOrBikePartSingleSaleDTO();
            laborOrBikePartSingleSaleDTO.setId(laborOrBikePartSingleSale.getId());
            laborOrBikePartSingleSaleDTO.setClient(laborOrBikePartSingleSale.getSingleSale().getClient());
            laborOrBikePartSingleSaleDTO.setCreatedAt(laborOrBikePartSingleSale.getSingleSale().getCreatedAt());
            laborOrBikePartSingleSaleDTO.setBikePartQuantity(laborOrBikePartSingleSale.getQuantity());
            laborOrBikePartSingleSaleDTOList.add(laborOrBikePartSingleSaleDTO);
        }
        return laborOrBikePartSingleSaleDTOList;
    }
}
