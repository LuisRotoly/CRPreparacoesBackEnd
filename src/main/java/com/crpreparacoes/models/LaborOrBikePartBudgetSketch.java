package com.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="labor_or_bike_part_budget_sketch")
public class LaborOrBikePartBudgetSketch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_labor_or_bike_part_budget_sketch")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "value")
    private double value;

    @OneToOne
    @JoinColumn(name="budget_sketch_id", referencedColumnName = "id_budget_sketch")
    private BudgetSketch budgetSketch;
}
