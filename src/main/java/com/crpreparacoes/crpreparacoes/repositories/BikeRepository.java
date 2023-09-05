package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.models.Bike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends CrudRepository<Bike, Integer> {

    @Query(value = "SELECT b FROM Bike b")
    List<Bike> listAllBikes();

    @Query(value = "SELECT b FROM Bike b WHERE b.name = :name AND b.brand = :brand AND b.year = :year AND b.engineCapacity = :engineCapacity")
    Bike findBikeExists(String name, String brand, String year, Integer engineCapacity);

    @Query(value = "SELECT b FROM Bike b WHERE b.name LIKE %:word% OR b.brand LIKE %:word%")
    List<Bike> filterListBikes(String word);
}
