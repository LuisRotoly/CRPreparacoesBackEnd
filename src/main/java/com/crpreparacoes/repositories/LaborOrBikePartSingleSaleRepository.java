package com.crpreparacoes.repositories;

import com.crpreparacoes.models.LaborOrBikePartSingleSale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaborOrBikePartSingleSaleRepository extends CrudRepository<LaborOrBikePartSingleSale, Long> {

    @Query(value = "SELECT l FROM LaborOrBikePartSingleSale l WHERE l.name = :bikePartName ORDER BY l.singleSale.createdAt DESC")
    List<LaborOrBikePartSingleSale> getLaborOrBikePartBudgetByBikePartId(String bikePartName);
}
