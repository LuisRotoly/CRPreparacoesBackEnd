package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.models.Bike;
import com.crpreparacoes.crpreparacoes.models.Part;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PartRepository extends CrudRepository<Part, Integer> {

    @Query(value = "SELECT p FROM Part p")
    List<Part> listAllParts();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Part p SET p.name = :name, p.value = :value, p.stockQuantity = :stockQuantity, p.bike.id = :bikeId, p.updatedAt = :updatedAt WHERE p.id = :idPart")
    void editPartById(Long idPart, String name, Double value, Integer stockQuantity, Integer bikeId, LocalDateTime updatedAt);

    @Query(value = "SELECT p FROM Part p WHERE p.name = :name AND p.bike.id = :bikeId")
    Bike findByNameAndBike(String name, Integer bikeId);
}
