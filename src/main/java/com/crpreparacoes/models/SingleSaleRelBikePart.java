package com.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="single_sale_rel_bike_part")
public class SingleSaleRelBikePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_single_sale_rel_bike_part")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "value")
    private double value;

    @OneToOne
    @JoinColumn(name="bike_part_id", referencedColumnName = "id_bike_part")
    private BikePart bikePart;

    @OneToOne
    @JoinColumn(name="single_sale_id", referencedColumnName = "id_single_sale")
    private SingleSale singleSale;
}
