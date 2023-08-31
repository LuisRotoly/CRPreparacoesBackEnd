package com.crpreparacoes.crpreparacoes.controllers;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.supplier.CreateSupplier;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.supplier.EditSupplier;
import com.crpreparacoes.crpreparacoes.models.Supplier;
import com.crpreparacoes.crpreparacoes.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**Método para buscar todos os fornecedores
     * @return List<Supplier> - Lista de fornecedores
     */
    @RequestMapping(value="/listSuppliers", method = RequestMethod.GET)
    public @ResponseBody List<Supplier> listSuppliers(){
        return supplierService.listAllSuppliers();
    }

    /**Método para adicionar um fornecedor
     */
    @RequestMapping(value="/addSupplier", method = RequestMethod.POST)
    public @ResponseBody void addSupplier(@RequestBody CreateSupplier createSupplier){
        supplierService.addNewSupplier(createSupplier);
    }

    /**Método para editar um fornecedor
     */
    @RequestMapping(value="/editSupplier", method = RequestMethod.POST)
    public @ResponseBody void editSupplier(@RequestBody EditSupplier editSupplier){
        supplierService.editSupplierById(editSupplier);
    }
}
