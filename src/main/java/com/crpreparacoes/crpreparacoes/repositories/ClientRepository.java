package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    @Query(value = "SELECT c FROM Client c")
    List<Client> listAllClients();

    @Query(value = "SELECT c FROM Client c WHERE c.cpfcnpj = :cpfcnpj")
    Client findByCpfcnpj(String cpfcnpj);

    @Query(value = "SELECT c FROM Client c WHERE c.name LIKE %:word% OR c.nickname LIKE %:word% OR c.cpfcnpj LIKE %:word%")
    List<Client> filterListClients(String word);
}
