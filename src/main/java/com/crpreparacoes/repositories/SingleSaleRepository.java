package com.crpreparacoes.repositories;

import com.crpreparacoes.models.SingleSale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SingleSaleRepository extends CrudRepository<SingleSale, Long> {

    @Query(value = "SELECT s FROM SingleSale s WHERE s.createdAt >= :monthAgo ORDER BY s.createdAt DESC")
    List<SingleSale> listAllSingleSaleFinishedByaMonth(LocalDateTime monthAgo);

    @Query(value = "SELECT s FROM SingleSale s WHERE s.client LIKE %:word% ORDER BY s.createdAt DESC")
    List<SingleSale> filterListSingleSaleFinance(String word);

    @Query(value = "SELECT s FROM SingleSale s ORDER BY s.createdAt DESC")
    List<SingleSale> listAllSingleSale();
}
