package com.crpreparacoes.repositories;

import com.crpreparacoes.models.BikeService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeServiceRepository extends CrudRepository<BikeService, Long> {

    @Query(value = "SELECT b FROM BikeService b")
    List<BikeService> listAllBikeServices();

    @Query(value = "SELECT b FROM BikeService b WHERE b.name LIKE %:word%")
    List<BikeService> filterListBikeServices(String word);
}
