package com.crpreparacoes.repositories;

import com.crpreparacoes.models.LaborOrBikePartBudgetSketch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaborOrBikePartBudgetSketchRepository extends CrudRepository<LaborOrBikePartBudgetSketch,Long> {

    @Query(value = "SELECT l FROM LaborOrBikePartBudgetSketch l WHERE l.budgetSketch.id = :budgetId")
    List<LaborOrBikePartBudgetSketch> findAllLaborOrBikePartBudgetSketchById(Long budgetId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM LaborOrBikePartBudgetSketch l WHERE l.budgetSketch.id = :budgetSketchId")
    void deleteAllByBudgetSketchId(Long budgetSketchId);
}
