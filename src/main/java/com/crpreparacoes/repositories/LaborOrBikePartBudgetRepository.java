package com.crpreparacoes.repositories;

import com.crpreparacoes.models.LaborOrBikePartBudget;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaborOrBikePartBudgetRepository extends CrudRepository<LaborOrBikePartBudget,Long> {

    @Query(value = "SELECT l FROM LaborOrBikePartBudget l WHERE l.budget.id = :budgetId")
    List<LaborOrBikePartBudget> findAllLaborOrBikePartBudgetById(Long budgetId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM LaborOrBikePartBudget l WHERE l.budget.id = :budgetId")
    void deleteAllByBudgetId(Long budgetId);
}
