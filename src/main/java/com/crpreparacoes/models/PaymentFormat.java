package com.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payment_format")
public class PaymentFormat{

    public enum PaymentFormatEnum {
        CASH(1L, "Dinheiro"),
        DEBIT_CARD(2L, "Cartão de débito"),
        CREDIT_CARD(3L, "Cartão de crédito"),
        PIX(4L, "Pix"),
        BANK_TRANSFER(5L, "Transferência"),
        PAYCHECK(6L, "Cheque"),
        OTHER(7L, "Outro");

        public final Long id;
        public final String type;
        PaymentFormatEnum(Long id, String type) {
            this.id = id;
            this.type = type;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_format")
    private Long id;

    @Column(name = "type")
    private String type;

    public PaymentFormat(PaymentFormatEnum paymentFormatEnum){
        this.id = paymentFormatEnum.id;
        this.type = paymentFormatEnum.type;
    }
}
