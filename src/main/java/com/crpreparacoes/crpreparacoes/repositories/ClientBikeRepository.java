package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.models.ClientBike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientBikeRepository extends CrudRepository<ClientBike, Integer> {

    @Query(value = "SELECT cb FROM ClientBike cb WHERE cb.client.id = :clientId")
    List<ClientBike> listAllClientBikeByClientId(Long clientId);

    @Query(value = "SELECT cb FROM ClientBike cb WHERE cb.plate = :plate")
    ClientBike findByPlate(String plate);
}
