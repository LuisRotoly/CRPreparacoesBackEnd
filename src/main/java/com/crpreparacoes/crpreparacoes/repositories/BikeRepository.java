package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.models.Bike;
import com.crpreparacoes.crpreparacoes.models.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BikeRepository extends CrudRepository<Bike, Integer> {

    @Query(value = "SELECT b FROM Bike b")
    List<Client> listAllBikes();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Bike b SET b.name = :name, b.brand = :brand, b.engineCapacity = :engineCapacity, b.year = :year, b.updatedAt = :updatedAt WHERE b.id = :idBike")
    void editBikeById(Long idBike, String name, String brand, Integer engineCapacity, String year, LocalDateTime updatedAt);

    @Query(value = "SELECT b FROM Bike b WHERE b.name = :name AND b.brand = :brand AND b.year = :year AND b.engineCapacity = :engineCapacity")
    Bike findBikeExists(String name, String brand, String year, Integer engineCapacity);
}
