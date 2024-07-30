package com.crpreparacoes.dto;

import com.crpreparacoes.models.PaymentFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashHandlingDTO {
    private Long id;
    private String description;
    private String notes;
    private double value;
    private PaymentFormat paymentFormat;
    private LocalDateTime paidAt;
    private boolean isFreeInput;
}
