package com.crpreparacoes.repositories;

import com.crpreparacoes.models.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BudgetRepository extends CrudRepository<Budget, Long> {

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.createdAt >= :twoWeeksAgo ORDER BY b.createdAt DESC")
    List<Budget> listAllBudgets(LocalDateTime twoWeeksAgo);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND (b.plate LIKE %:word% OR b.client.name LIKE %:word% OR b.client.cpfcnpj LIKE %:word%) AND b.status.id = :statusId ORDER BY b.createdAt DESC")
    List<Budget> filterListBudgets(String word, Long statusId);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.createdAt >= :twoWeeksAgo AND (b.plate LIKE %:word% OR b.client.name LIKE %:word% OR b.client.cpfcnpj LIKE %:word%) ORDER BY b.createdAt DESC")
    List<Budget> filterListBudgetsWithoutStatus(String word, LocalDateTime twoWeeksAgo);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.createdAt >= :twoWeeksAgo AND b.status.id = :statusId ORDER BY b.createdAt DESC")
    List<Budget> filterListBudgetsWithoutWord(Long statusId, LocalDateTime twoWeeksAgo);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.status.id = :statusId AND (b.plate LIKE %:word% OR b.client.name LIKE %:word% OR b.client.nickname LIKE %:word%) ORDER BY b.updatedAt DESC")
    List<Budget> filterListFinanceBudgets(String word, Long statusId);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.client.id = :clientId AND b.plate = :plate AND (b.status.id != :statusIdFinished AND b.status.id != :statusIdCanceled)")
    Budget findBudgetNotFinishedByPlate(Long clientId, String plate, Long statusIdFinished, Long statusIdCanceled);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.status.id = :statusId ORDER BY b.createdAt DESC")
    List<Budget> listAllBudgetsFinished(Long statusId);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.updatedAt >= :monthAgo AND b.status.id = :statusId ORDER BY b.createdAt DESC")
    List<Budget> listAllBudgetsFinishedByaMonth(Long statusId, LocalDateTime monthAgo);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.client.id = :clientId ORDER BY b.createdAt DESC")
    List<Budget> listAllBudgetsByClientId(Long clientId);

    @Query(value = "SELECT b FROM Budget b WHERE b.isRemoved != true AND b.status.id = :statusId AND year(b.updatedAt) =:year ORDER BY b.updatedAt ASC")
    List<Budget> listAllBudgetsFinishedAtYear(Long statusId, String year);
}
