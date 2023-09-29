package com.crpreparacoes.repositories;

import com.crpreparacoes.models.BikeBrand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeBrandRepository extends CrudRepository<BikeBrand, Long> {

    @Query(value = "SELECT b FROM BikeBrand b ORDER BY b.name")
    List<BikeBrand> listAllBikeBrands();
}
