package com.crpreparacoes.repositories;

import com.crpreparacoes.models.BikePart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikePartRepository extends CrudRepository<BikePart, Long> {

    @Query(value = "SELECT b FROM BikePart b")
    List<BikePart> listAllBikeParts();

    @Query(value = "SELECT b FROM BikePart b WHERE b.name LIKE %:word%")
    List<BikePart> filterListBikes(String word);

    @Query(value = "SELECT b FROM BikePart b WHERE b.name = :name")
    BikePart findByName(String name);
}
