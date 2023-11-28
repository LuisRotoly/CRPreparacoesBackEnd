package com.crpreparacoes.repositories;

import com.crpreparacoes.models.SingleSale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleSaleRepository extends CrudRepository<SingleSale, Long> {
}
