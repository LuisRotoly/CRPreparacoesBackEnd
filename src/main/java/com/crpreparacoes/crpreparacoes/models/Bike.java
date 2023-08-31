package com.crpreparacoes.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="bike")
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bike")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="brand")
    private String brand;

    @Column(name="engine_capacity")
    private Integer engineCapacity;

    @Column(name="year")
    private String year;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
