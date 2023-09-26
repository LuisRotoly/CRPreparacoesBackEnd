package com.crpreparacoes.repositories;

import com.crpreparacoes.models.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    @Query(value = "SELECT s FROM Supplier s WHERE s.name = :name")
    Supplier findByName(String name);

    @Query(value = "SELECT s FROM Supplier s")
    List<Supplier> listAllSuppliers();

    @Query(value = "SELECT s FROM Supplier s WHERE s.name LIKE %:word% OR s.phone LIKE %:word%")
    List<Supplier> filterListSuppliers(String word);
}
