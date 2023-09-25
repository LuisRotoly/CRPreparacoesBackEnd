package com.crpreparacoes.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bike_service")
public class BikeService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bike_service")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="value")
    private Double value;
}
