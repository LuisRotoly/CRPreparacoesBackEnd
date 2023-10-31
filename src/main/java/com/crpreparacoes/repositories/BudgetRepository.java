package com.crpreparacoes.repositories;

import com.crpreparacoes.models.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends CrudRepository<Budget, Long> {

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true ORDER BY b.createdAt DESC")
    List<Budget> listAllBudgets();

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND (b.plate LIKE %:word% OR b.client.name LIKE %:word% OR b.client.cpfcnpj LIKE %:word%) ORDER BY b.createdAt DESC")
    List<Budget> filterListBudgets(String word);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.status.id = :statusId AND (b.plate LIKE %:word% OR b.client.name LIKE %:word%) ORDER BY b.updatedAt DESC")
    List<Budget> filterListFinanceBudgets(String word, Long statusId);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.client.id = :clientId AND b.plate = :plate AND (b.status.id != :statusIdFinished AND b.status.id != :statusIdCanceled)")
    Budget findBudgetNotFinishedByPlate(Long clientId, String plate, Long statusIdFinished, Long statusIdCanceled);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.status.id = :statusId ORDER BY b.createdAt DESC")
    List<Budget> listAllBudgetsFinished(Long statusId);
}
