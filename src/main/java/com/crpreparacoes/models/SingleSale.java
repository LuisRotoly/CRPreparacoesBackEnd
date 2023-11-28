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
@Table(name="single_sale")
public class SingleSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_single_sale")
    private Long id;

    @Column(name="client")
    private String client;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}
