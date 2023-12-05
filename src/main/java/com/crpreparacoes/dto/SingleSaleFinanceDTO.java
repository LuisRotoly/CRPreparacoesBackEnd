package com.crpreparacoes.dto;

import com.crpreparacoes.models.SingleSaleFinance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleSaleFinanceDTO {
    private Long SingleSaleId;
    private String clientName;
    private List<SingleSaleFinance> singleSaleFinanceList;
    private LocalDateTime finalizedAt;
    private double totalValue;
    private double toBePaid;
}
