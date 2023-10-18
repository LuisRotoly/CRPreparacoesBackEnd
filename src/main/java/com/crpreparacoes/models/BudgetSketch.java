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
@Table(name="budget_sketch")
public class BudgetSketch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_budget_sketch")
    private Long id;

    @Column(name="client")
    private String client;

    @Column(name="plate")
    private String plate;

    @Column(name="bike")
    private String bike;

    @Column(name="phone")
    private String phone;

    @Column(name="notes")
    private String notes;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
