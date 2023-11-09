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
@Table(name="finance_budget")
public class FinanceBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_finance_budget")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "budget_id", referencedColumnName = "id_budget")
    private Budget budget;

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
