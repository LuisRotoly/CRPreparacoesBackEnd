package com.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_budget")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "client_id", referencedColumnName = "id_client")
    private Client client;

    @Column(name="plate")
    private String plate;

    @Column(name="bike_name")
    private String bikeName;

    @Column(name="bike_brand")
    private String bikeBrand;

    @Column(name="engine_capacity")
    private Integer engineCapacity;

    @Column(name="year")
    private String year;

    @OneToOne
    @JoinColumn(name="status_id", referencedColumnName = "id_status")
    private Status status;

    @Column(name="total_value")
    private double totalValue;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
