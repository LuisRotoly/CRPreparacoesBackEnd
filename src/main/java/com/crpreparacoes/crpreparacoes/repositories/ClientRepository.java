package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.client.CreateClient;
import com.crpreparacoes.crpreparacoes.models.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    @Query(value = "SELECT c FROM Client c")
    List<Client> listAllClients();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Client c SET c.name = :name, c.cpfcnpj = :cpfcnpj, c.phone = :phone, c.nickname = :nickname, c.address = :address, c.updatedAt = :updatedAt WHERE c.id = :idClient")
    void editClientById(Long idClient, String name, String cpfcnpj, String phone, String nickname, String address, LocalDateTime updatedAt);

    @Query(value = "SELECT c FROM Client c WHERE c.cpfcnpj = :cpfcnpj")
    Client findByCpfcnpj(String cpfcnpj);
}
