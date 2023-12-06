package com.crpreparacoes.repositories;

import com.crpreparacoes.models.SingleSaleRelBikePart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleSaleRelBikePartRepository extends CrudRepository<SingleSaleRelBikePart, Long> {

    @Query(value = "SELECT s FROM SingleSaleRelBikePart s WHERE s.bikePart.id = :bikePartId ORDER BY s.singleSale.createdAt DESC")
    List<SingleSaleRelBikePart> getSingleSaleByBikePartId(Long bikePartId);

    @Query(value = "SELECT s FROM SingleSaleRelBikePart s WHERE s.singleSale.id = :singleSaleId")
    List<SingleSaleRelBikePart> findAllLaborOrBikePartBudgetById(Long singleSaleId);
}
