package com.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bike_part")
public class BikePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bike_part")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="value")
    private Double value;

    @Column(name="profit_percentage")
    private Double profitPercentage;

    @Column(name="stock_quantity")
    private Integer stockQuantity;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="notes")
    private String notes;
}
