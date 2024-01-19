package com.crpreparacoes.repositories;

import com.crpreparacoes.dto.ReportDTO;
import com.crpreparacoes.models.FinanceBudget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceBudgetRepository extends CrudRepository<FinanceBudget, Long> {

    @Query(value = "SELECT SUM(f.value) FROM FinanceBudget f WHERE f.budget.id = :budgetId")
    Double getSumPaidValue(Long budgetId);

    @Query(value = "SELECT f FROM FinanceBudget f WHERE f.budget.id = :budgetId ORDER BY f.paidAt DESC")
    List<FinanceBudget> findAllFinanceBudgetByBudgetId(Long budgetId);

    @Query(value = "SELECT MONTHNAME(paid_at) as `month`, SUM(value) as `totalValue` FROM finance_budget WHERE YEAR(paid_at) = :year GROUP BY MONTH(paid_at)", nativeQuery = true)
    List<ReportDTO> getMonthAndValueSumFromBudget(String year);
}
