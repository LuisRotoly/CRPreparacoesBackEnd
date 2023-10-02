package com.crpreparacoes.repositories;

import com.crpreparacoes.models.Bike;
import com.crpreparacoes.models.BikePart;
import com.crpreparacoes.models.BikePartRelBike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikePartRelBikeRepository extends CrudRepository<BikePartRelBike, Long> {

    @Query(value = "SELECT b.bike FROM BikePartRelBike b WHERE b.bikePart.id = :bikePartId")
    List<Bike> findBikesByBikePartId(Long bikePartId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM BikePartRelBike b WHERE b.bikePart.id = :bikePartId")
    void deleteAllByBikePartId(Long bikePartId);

    @Query(value = "SELECT b.bikePart FROM BikePartRelBike b WHERE b.bikePart.name LIKE %:word% OR b.bike.name LIKE %:word% OR b.bike.bikeBrand.name LIKE %:word%")
    List<BikePart> filterListBikes(String word);

    @Query(value = "SELECT b.bikePart FROM BikePartRelBike b WHERE b.bike.id = :bikeId")
    List<BikePart> listBikePartByBikeId(Long bikeId);
}
