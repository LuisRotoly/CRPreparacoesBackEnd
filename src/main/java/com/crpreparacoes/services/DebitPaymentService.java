package com.crpreparacoes.services;

import com.crpreparacoes.bodyrequestinput.debitPayment.CreateDebitPaymentRequest;
import com.crpreparacoes.dto.CashHandlingDTO;
import com.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.models.DebitPayment;
import com.crpreparacoes.models.FinanceBudget;
import com.crpreparacoes.repositories.DebitPaymentRepository;
import com.crpreparacoes.repositories.FinanceBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DebitPaymentService {

    @Autowired
    private DebitPaymentRepository debitPaymentRepository;

    @Autowired
    private FinanceBudgetRepository financeBudgetRepository;

    public void removeDebitPaymentById(Long id) {
        try {
            debitPaymentRepository.deleteById(id);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar remover pagamento!");
        }
    }

    public void addDebitPayment(CreateDebitPaymentRequest createDebitPaymentRequest) {
        DebitPayment debitPayment = new DebitPayment();
        debitPayment.setDescription(createDebitPaymentRequest.getDescription());
        debitPayment.setNotes(createDebitPaymentRequest.getNotes());
        debitPayment.setPaymentFormat(createDebitPaymentRequest.getPaymentFormat());
        debitPayment.setValue(createDebitPaymentRequest.getPaymentValue());
        debitPayment.setPaidAt(LocalDateTime.now());
        try {
            debitPaymentRepository.save(debitPayment);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar pagamento!");
        }
    }

    public List<CashHandlingDTO> getCashHandlingListByDate(String date) {
        List<DebitPayment> debitPaymentList = debitPaymentRepository.findAllDebitPaymentListByDate(date);
        List<FinanceBudget> financeBudgetList = financeBudgetRepository.findAllCreditPaymentListByDate(date);
        List<CashHandlingDTO> cashHandlingDTOList = new ArrayList<>();
        for (FinanceBudget financeBudget: financeBudgetList) {
            CashHandlingDTO cashHandlingDTO = new CashHandlingDTO();
            cashHandlingDTO.setId(financeBudget.getId());
            cashHandlingDTO.setDescription(financeBudget.getBudget().getClient().getName()+" - "+financeBudget.getBudget().getBikeName());
            cashHandlingDTO.setNotes(financeBudget.getNotes());
            cashHandlingDTO.setValue(financeBudget.getValue());
            cashHandlingDTO.setPaymentFormat(financeBudget.getPaymentFormat());
            cashHandlingDTO.setPaidAt(financeBudget.getPaidAt());
            cashHandlingDTO.setFreeInput(false);
            cashHandlingDTOList.add(cashHandlingDTO);
        }
        for (DebitPayment debitPayment: debitPaymentList) {
            CashHandlingDTO cashHandlingDTO = new CashHandlingDTO();
            cashHandlingDTO.setId(debitPayment.getId());
            cashHandlingDTO.setDescription(debitPayment.getDescription());
            cashHandlingDTO.setNotes(debitPayment.getNotes());
            cashHandlingDTO.setValue(debitPayment.getValue());
            cashHandlingDTO.setPaymentFormat(debitPayment.getPaymentFormat());
            cashHandlingDTO.setPaidAt(debitPayment.getPaidAt());
            cashHandlingDTO.setFreeInput(true);
            cashHandlingDTOList.add(cashHandlingDTO);
        }
        return cashHandlingDTOList;
    }
}
