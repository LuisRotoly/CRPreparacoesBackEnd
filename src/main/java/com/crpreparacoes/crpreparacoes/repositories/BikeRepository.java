package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.models.Bike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends CrudRepository<Bike, Long> {

    @Query(value = "SELECT b FROM Bike b")
    List<Bike> listAllBikes();

    @Query(value = "SELECT b FROM Bike b JOIN b.bikeBrand br WHERE b.name = :name AND br.id = :brandId AND b.year = :year AND b.engineCapacity = :engineCapacity")
    Bike findBikeExists(String name, Long brandId, String year, Integer engineCapacity);

    @Query(value = "SELECT b FROM Bike b JOIN b.bikeBrand br WHERE b.name LIKE %:word% OR br.name LIKE %:word%")
    List<Bike> filterListBikes(String word);
}
