package com.crpreparacoes.repositories;

import com.crpreparacoes.models.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends CrudRepository<Budget, Long> {

    @Query(value = "SELECT b FROM Budget b ORDER BY b.createdAt DESC")
    List<Budget> listAllBudgets();

    @Query(value = "SELECT b FROM Budget b WHERE b.plate LIKE %:word% OR b.client.name LIKE %:word% OR b.client.cpfcnpj LIKE %:word% ORDER BY b.createdAt DESC")
    List<Budget> filterListBudgets(String word);

    @Query(value = "SELECT b FROM Budget b WHERE b.plate = :plate AND (b.status.id != :statusIdFinished AND b.status.id != :statusIdCanceled)")
    Budget findBudgetNotFinishedByPlate(String plate, Long statusIdFinished, Long statusIdCanceled);
}
