package com.crpreparacoes.repositories;

import com.crpreparacoes.models.SingleSaleFinance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleSaleFinanceRepository  extends CrudRepository<SingleSaleFinance, Long> {

    @Query(value = "SELECT SUM(s.value) FROM SingleSaleFinance s WHERE s.singleSale.id = :singleSaleId")
    Double getSingleSaleSumPaidValue(Long singleSaleId);

    @Query(value = "SELECT s FROM SingleSaleFinance s WHERE s.singleSale.id = :singleSaleId ORDER BY s.paidAt DESC")
    List<SingleSaleFinance> findAllSingleSaleFinanceBysingleSaleId(Long singleSaleId);
}
