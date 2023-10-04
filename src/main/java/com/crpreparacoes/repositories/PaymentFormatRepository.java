package com.crpreparacoes.repositories;

import com.crpreparacoes.models.PaymentFormat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentFormatRepository extends CrudRepository<PaymentFormat, Long> {

    @Query("SELECT p FROM PaymentFormat p")
    List<PaymentFormat> listAllPaymentFormat();

    @Query("SELECT p FROM PaymentFormat p WHERE p.type = :type")
    PaymentFormat findByType(String type);
}
