package com.crpreparacoes.repositories;

import com.crpreparacoes.models.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends CrudRepository<Status,Long> {

    @Query("SELECT s FROM Status s")
    List<Status> listAllStatus();

    @Query("SELECT s FROM Status s WHERE s.description = :description")
    Status findByDescription(String description);
}
