package com.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="single_sale_finance")
public class SingleSaleFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_single_sale_finance")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "single_sale_id", referencedColumnName = "id_single_sale")
    private SingleSale singleSale;

    @Column(name="value")
    private double value;

    @Column(name="notes")
    private String notes;

    @OneToOne
    @JoinColumn(name="payment_format_id", referencedColumnName = "id_payment_format")
    private PaymentFormat paymentFormat;

    @Column(name="paid_at")
    private LocalDateTime paidAt;
}
