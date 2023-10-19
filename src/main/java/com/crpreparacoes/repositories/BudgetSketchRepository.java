package com.crpreparacoes.repositories;

import com.crpreparacoes.models.BudgetSketch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetSketchRepository extends CrudRepository<BudgetSketch,Long> {

    @Query(value = "SELECT b FROM BudgetSketch b ORDER BY b.createdAt DESC")
    List<BudgetSketch> listAllBudgetsSketch();

    @Query(value = "SELECT b FROM BudgetSketch b WHERE b.plate LIKE %:word% OR b.client LIKE %:word% OR b.bike LIKE %:word% ORDER BY b.createdAt DESC")
    List<BudgetSketch> filterListBudgetsSketch(String word);
}
