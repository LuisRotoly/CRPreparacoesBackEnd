package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.singleSaleFinance.CreateSingleSaleFinanceRequest;
import com.crpreparacoes.dto.SingleSaleFinanceDTO;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.*;
import com.crpreparacoes.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SingleSaleFinanceService {

    @Autowired
    private SingleSaleFinanceRepository singleSaleFinanceRepository;

    @Autowired
    private SingleSaleRepository singleSaleRepository;

    @Autowired
    private SingleSaleRelBikePartRepository singleSaleRelBikePartRepository;

    public List<SingleSaleFinanceDTO> listAllSingleSaleFinance() {
        List<SingleSale> singleSaleList = singleSaleRepository.listAllSingleSaleFinishedByaMonth(LocalDateTime.now().minusMonths(1));
        List<SingleSaleFinanceDTO> singleSaleFinanceDTOList = new ArrayList<>();
        for (SingleSale singleSale:singleSaleList) {
            SingleSaleFinanceDTO singleSaleFinanceDTO = new SingleSaleFinanceDTO();
            singleSaleFinanceDTO.setSingleSaleId(singleSale.getId());
            singleSaleFinanceDTO.setClientName(singleSale.getClient());
            singleSaleFinanceDTO.setFinalizedAt(singleSale.getCreatedAt());
            double totalValue = getTotalvalue(singleSale.getId());
            singleSaleFinanceDTO.setTotalValue(totalValue);
            Double paidValue = singleSaleFinanceRepository.getSingleSaleSumPaidValue(singleSale.getId());
            if(paidValue != null) {
                singleSaleFinanceDTO.setToBePaid(totalValue-paidValue);
            }else{
                singleSaleFinanceDTO.setToBePaid(totalValue);
            }
            singleSaleFinanceDTOList.add(singleSaleFinanceDTO);
        }
        return singleSaleFinanceDTOList;
    }

    private double getTotalvalue(Long singleSaleId){
        double totalValue = 0;
        List<SingleSaleRelBikePart> singleSaleRelBikePartList = singleSaleRelBikePartRepository.findAllLaborOrBikePartBudgetById(singleSaleId);
        for (SingleSaleRelBikePart singleSaleRelBikePart:singleSaleRelBikePartList) {
            totalValue = totalValue+singleSaleRelBikePart.getValue()*singleSaleRelBikePart.getQuantity();
        }
        return totalValue;
    }

    public List<SingleSaleFinanceDTO> filterSingleSaleFinanceListRequest(String word, boolean isInDebit, boolean isPaid) {
        List<SingleSale> singleSaleList = singleSaleRepository.filterListSingleSaleFinance(word);
        List<SingleSaleFinanceDTO> singleSaleFinanceDTOList = new ArrayList<>();
        for (SingleSale singleSale:singleSaleList) {
            SingleSaleFinanceDTO singleSaleFinanceDTO = new SingleSaleFinanceDTO();
            singleSaleFinanceDTO.setSingleSaleId(singleSale.getId());
            singleSaleFinanceDTO.setClientName(singleSale.getClient());
            singleSaleFinanceDTO.setFinalizedAt(singleSale.getCreatedAt());
            double totalValue = getTotalvalue(singleSale.getId());
            singleSaleFinanceDTO.setTotalValue(totalValue);
            Double paidValue = singleSaleFinanceRepository.getSingleSaleSumPaidValue(singleSale.getId());
            if(paidValue != null) {
                singleSaleFinanceDTO.setToBePaid(totalValue-paidValue);
            }else{
                singleSaleFinanceDTO.setToBePaid(totalValue);
            }
            if(isInDebit && singleSaleFinanceDTO.getToBePaid()>0) {
                singleSaleFinanceDTOList.add(singleSaleFinanceDTO);
            }else if(isPaid && singleSaleFinanceDTO.getToBePaid()<=0){
                singleSaleFinanceDTOList.add(singleSaleFinanceDTO);
            }else if(!isInDebit && !isPaid){
                singleSaleFinanceDTOList.add(singleSaleFinanceDTO);
            }
        }
        return singleSaleFinanceDTOList;
    }

    public SingleSaleFinanceDTO listSingleSaleFinanceById(Long singleSaleId) {
        SingleSale singleSale = singleSaleRepository.findById(singleSaleId).get();
        SingleSaleFinanceDTO singleSaleFinanceDTO = new SingleSaleFinanceDTO();

        singleSaleFinanceDTO.setSingleSaleId(singleSale.getId());
        singleSaleFinanceDTO.setClientName(singleSale.getClient());
        singleSaleFinanceDTO.setFinalizedAt(singleSale.getCreatedAt());
        double totalValue = getTotalvalue(singleSale.getId());
        singleSaleFinanceDTO.setTotalValue(totalValue);
        Double paidValue = singleSaleFinanceRepository.getSingleSaleSumPaidValue(singleSale.getId());
        if(paidValue != null) {
            singleSaleFinanceDTO.setToBePaid(totalValue-paidValue);
        }else{
            singleSaleFinanceDTO.setToBePaid(totalValue);
        }
        singleSaleFinanceDTO.setSingleSaleFinanceList(singleSaleFinanceRepository.findAllSingleSaleFinanceBysingleSaleId(singleSaleId));
        return singleSaleFinanceDTO;
    }

    public List<SingleSaleFinance> getSingleSalePaymentsListById(Long singleSaleId) {
        return singleSaleFinanceRepository.findAllSingleSaleFinanceBysingleSaleId(singleSaleId);
    }

    public void removeSingleSalePaymentById(Long id) {
        try {
            singleSaleFinanceRepository.deleteById(id);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar remover pagamento!");
        }
    }

    public void addSingleSalePayment(CreateSingleSaleFinanceRequest createSingleSaleFinanceRequest) {
        SingleSaleFinance singleSaleFinance = new SingleSaleFinance();
        singleSaleFinance.setSingleSale(singleSaleRepository.findById(createSingleSaleFinanceRequest.getSingleSaleId()).get());
        singleSaleFinance.setPaymentFormat(createSingleSaleFinanceRequest.getPaymentFormat());
        singleSaleFinance.setValue(createSingleSaleFinanceRequest.getPaymentValue());
        singleSaleFinance.setPaidAt(LocalDateTime.now());
        singleSaleFinance.setNotes(createSingleSaleFinanceRequest.getNotes());
        try {
            singleSaleFinanceRepository.save(singleSaleFinance);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar pagamento!");
        }
    }

    public double getSingleSaleToBePaidById(Long singleSaleId) {
        SingleSale singleSale = singleSaleRepository.findById(singleSaleId).get();
        double totalValue = getTotalvalue(singleSale.getId());
        Double paidValue = singleSaleFinanceRepository.getSingleSaleSumPaidValue(singleSale.getId());
        if(paidValue != null) {
            return totalValue-paidValue;
        }else{
            return totalValue;
        }
    }

    public double getSingleSaleTotalToReceive() {
        List<SingleSale> singleSaleList = singleSaleRepository.listAllSingleSale();
        double sumValue = 0;
        for (SingleSale singleSale:singleSaleList) {
            double totalSingleSaleValue = getTotalvalue(singleSale.getId());
            Double paidValue = singleSaleFinanceRepository.getSingleSaleSumPaidValue(singleSale.getId());
            if(paidValue != null) {
                sumValue = sumValue + (totalSingleSaleValue - paidValue);
            }else{
                sumValue = sumValue + totalSingleSaleValue;
            }
        }
        return sumValue;
    }
}
