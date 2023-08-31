package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.models.ClientBikes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClientBikesRepository extends CrudRepository<ClientBikes, Integer> {

    @Query(value = "SELECT cb FROM ClientBikes cb WHERE cb.client.id = :clientId")
    List<ClientBikes> listAllClientBikes(Long clientId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ClientBikes cb SET cb.client.id = :clientId, cb.bike.id = :bikeId, cb.plate = :plate, cb.updatedAt = :updatedAt WHERE cb.id = :idClientBikes")
    void editClientBikesById(Long idClientBikes, Integer clientId, Integer bikeId, String plate, LocalDateTime updatedAt);

    @Query(value = "SELECT cb FROM ClientBikes cb WHERE cb.plate = :plate")
    ClientBikes findByPlate(String plate);
}
