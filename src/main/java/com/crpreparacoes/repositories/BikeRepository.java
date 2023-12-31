package com.crpreparacoes.repositories;

import com.crpreparacoes.models.Bike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends CrudRepository<Bike, Long> {

    @Query(value = "SELECT b FROM Bike b ORDER BY b.name")
    List<Bike> listAllBikes();

    @Query(value = "SELECT b FROM Bike b JOIN b.bikeBrand br WHERE b.name = :name AND br.id = :brandId")
    Bike findBikeExists(String name, Long brandId);

    @Query(value = "SELECT b FROM Bike b JOIN b.bikeBrand br WHERE b.name LIKE %:word% OR br.name LIKE %:word% ORDER BY b.name")
    List<Bike> filterListBikes(String word);
}
