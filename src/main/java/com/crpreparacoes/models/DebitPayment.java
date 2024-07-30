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
@Table(name="debit_payment")
public class DebitPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_debit_payment")
    private Long id;

    @Column(name="description")
    private String description;

    @Column(name="notes")
    private String notes;

    @OneToOne
    @JoinColumn(name="payment_format_id", referencedColumnName = "id_payment_format")
    private PaymentFormat paymentFormat;

    @Column(name="value")
    private double value;

    @Column(name="paid_at")
    private LocalDateTime paidAt;
}
