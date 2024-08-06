package com.crpreparacoes.repositories;

import com.crpreparacoes.dto.ReportDTO;
import com.crpreparacoes.models.DebitPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebitPaymentRepository extends CrudRepository<DebitPayment, Long> {

    @Query(value = "SELECT * FROM debit_payment WHERE DATE(paid_at) = :date ORDER BY paid_at DESC", nativeQuery = true)
    List<DebitPayment> findAllDebitPaymentListByDate(String date);

    @Query(value = "SELECT MONTHNAME(paid_at) as `month`, SUM(value) as `totalValue` FROM debit_payment WHERE YEAR(paid_at) = :year AND value >= 0 GROUP BY MONTH(paid_at)", nativeQuery = true)
    List<ReportDTO> getMonthAndValueSumFromDebitPayment(String year);

    @Query(value = "SELECT MONTHNAME(paid_at) as `month`, SUM(value) as `totalValue` FROM debit_payment WHERE YEAR(paid_at) = :year AND value < 0 GROUP BY MONTH(paid_at)", nativeQuery = true)
    List<ReportDTO> getMonthAndNegativeValueSumFromDebitPayment(String year);

    @Query(value = "SELECT DISTINCT YEAR(paid_at) FROM debit_payment ORDER BY paid_at ASC", nativeQuery = true)
    List<String> getCashHandlingExistentYear();

    @Query(value = "SELECT * FROM debit_payment WHERE MONTH(paid_at) = :month AND YEAR(paid_at) = :year ORDER BY paid_at ASC;", nativeQuery = true)
    List<DebitPayment> getCashHandlingListByYearAndMonth(String year, int month);
}
