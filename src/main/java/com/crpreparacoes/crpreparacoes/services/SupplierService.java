package com.crpreparacoes.crpreparacoes.services;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.supplier.CreateSupplierRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.supplier.EditSupplierRequest;
import com.crpreparacoes.crpreparacoes.exception.ApiRequestException;
import com.crpreparacoes.crpreparacoes.models.Bike;
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

    public void addNewSupplier(CreateSupplierRequest createSupplierRequest) {
        if(supplierRepository.findByName(createSupplierRequest.getName()) != null){
            throw new ApiRequestException("Erro! O fornecedor já existe!");
        }
        Supplier supplier = new Supplier();
        supplier.setName(createSupplierRequest.getName());
        supplier.setPhone(createSupplierRequest.getPhone());
        supplier.setNotes(createSupplierRequest.getNotes());
        supplier.setCreatedAt(LocalDateTime.now());
        try {
            supplierRepository.save(supplier);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar adicionar o fornecedor!");
        }
    }

    public void editSupplierById(EditSupplierRequest editSupplierRequest) {
        Supplier supplier = new Supplier();
        supplier.setId(editSupplierRequest.getId());
        supplier.setName(editSupplierRequest.getName());
        supplier.setPhone(editSupplierRequest.getPhone());
        supplier.setNotes(editSupplierRequest.getNotes());
        supplier.setUpdatedAt(LocalDateTime.now());
        try {
            supplierRepository.save(supplier);
        }catch(Exception Error){
            throw new ApiRequestException("Erro ao tentar editar o fornecedor!");
        }
    }

    public List<Supplier> filterListSuppliers(String word) {
        return supplierRepository.filterListSuppliers(word);
    }

    public Supplier listSupplierById(Long id) {
        return supplierRepository.findById(Math.toIntExact(id)).get();
    }
}
