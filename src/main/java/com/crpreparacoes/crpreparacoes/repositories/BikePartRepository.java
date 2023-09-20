package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.models.BikePart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikePartRepository extends CrudRepository<BikePart, Long> {

    @Query(value = "SELECT p FROM BikePart p")
    List<BikePart> listAllBikeParts();

    @Query(value = "SELECT p FROM BikePart p WHERE p.name = :name AND p.bike.id = :bikeId")
    BikePart findByNameAndBike(String name, Long bikeId);

    @Query(value = "SELECT p FROM BikePart p JOIN p.bike b WHERE p.name LIKE %:word% OR b.name LIKE %:word% OR b.bikeBrand.name LIKE %:word%")
    List<BikePart> filterListBikes(String word);
}
