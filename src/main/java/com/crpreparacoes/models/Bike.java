package com.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bike")
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bike")
    private Long id;

    @Column(name="name")
    private String name;

    @OneToOne()
    @JoinColumn(name = "bike_brand_id", referencedColumnName = "id_bike_brand")
    private BikeBrand bikeBrand;

    @Column(name="engine_capacity")
    private Integer engineCapacity;

    @Column(name="year")
    private String year;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
