package com.crpreparacoes.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="client_bike")
public class ClientBike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client_bikes")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "client_id", referencedColumnName = "id_client")
    private Client client;

    @OneToOne()
    @JoinColumn(name = "bike_id", referencedColumnName = "id_bike")
    private Bike bike;

    @Column(name="plate")
    private String plate;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
