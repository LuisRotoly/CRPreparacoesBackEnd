package com.crpreparacoes.crpreparacoes.repositories;

import com.crpreparacoes.crpreparacoes.models.Supplier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Integer> {

    @Query(value = "SELECT s FROM Supplier s WHERE s.name = :name")
    Supplier findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Supplier s SET s.name = :name, s.phone = :phone, s.notes = :notes, s.updatedAt = :updatedAt WHERE s.id = :idSupplier")
    void editSupplierById(Long idSupplier, String name, String phone, String notes, LocalDateTime updatedAt);

    @Query(value = "SELECT s FROM Supplier s")
    List<Supplier> listAllSuppliers();
}
