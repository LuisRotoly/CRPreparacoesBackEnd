package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.supplier.CreateSupplier;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.supplier.EditSupplier;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.Supplier;
import com.crpreparacoes.crpreparacoes.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> listAllSuppliers() {
        return supplierRepository.listAllSuppliers();
    }

    public void addNewSupplier(CreateSupplier createSupplier) {
        if(supplierRepository.findByName(createSupplier.getName()) != null){
            throw new ApiRequestException("Erro! O fornecedor já existe!");
        }
        Supplier supplier = new Supplier();
        supplier.setName(createSupplier.getName());
        supplier.setPhone(createSupplier.getPhone());
        supplier.setNotes(createSupplier.getNotes());
        supplier.setCreatedAt(LocalDateTime.now());
        try {
            supplierRepository.save(supplier);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o fornecedor!");
        }
    }

    public void editSupplierById(EditSupplier editSupplier) {
        try {
            supplierRepository.editSupplierById(editSupplier.getId(),editSupplier.getName(),editSupplier.getPhone(),editSupplier.getNotes(),LocalDateTime.now());
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar o fornecedor!");
        }
    }
}
