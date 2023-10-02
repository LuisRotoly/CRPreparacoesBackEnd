package com.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bike_part_rel_bike")
public class BikePartRelBike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bike_part_rel_bike")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "bike_id", referencedColumnName = "id_bike")
    private Bike bike;

    @ManyToOne()
    @JoinColumn(name = "bike_part_id", referencedColumnName = "id_bike_part")
    private BikePart bikePart;
}
