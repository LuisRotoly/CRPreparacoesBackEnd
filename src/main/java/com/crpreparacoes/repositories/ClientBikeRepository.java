package com.crpreparacoes.repositories;

import com.crpreparacoes.models.Client;
import com.crpreparacoes.models.ClientBike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientBikeRepository extends CrudRepository<ClientBike, Long> {

    @Query(value = "SELECT cb FROM ClientBike cb WHERE cb.client.id = :clientId")
    List<ClientBike> listAllClientBikeByClientId(Long clientId);

    @Query(value = "SELECT cb FROM ClientBike cb WHERE cb.plate = :plate")
    ClientBike findByPlate(String plate);

    @Query(value = "SELECT cb.client FROM ClientBike cb WHERE cb.plate LIKE %:word%")
    List<Client> filterListClients(String word);
}
