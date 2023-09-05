package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.models.Bike;
import com.crpreparacoes.crpreparacoes.models.Part;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends CrudRepository<Part, Integer> {

    @Query(value = "SELECT p FROM Part p")
    List<Part> listAllParts();

    @Query(value = "SELECT p FROM Part p WHERE p.name = :name AND p.bike.id = :bikeId")
    Bike findByNameAndBike(String name, Integer bikeId);

    @Query(value = "SELECT p FROM Part p JOIN p.bike b WHERE p.name LIKE %:word% OR b.name LIKE %:word% OR b.brand LIKE %:word%")
    List<Part> filterListBikes(String word);
}
