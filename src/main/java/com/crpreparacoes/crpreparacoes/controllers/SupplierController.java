package com.crpreparacoes.crpreparacoes.controllers;

import com.crpreparacoes.crpreparacoes.bodyrequestinput.supplier.CreateSupplierRequest;
import com.crpreparacoes.crpreparacoes.bodyrequestinput.supplier.EditSupplierRequest;
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

    /**Método para buscar as motos usando um filtro
     * @return List<Supplier> - Lista de fornecedores
     */
    @RequestMapping(value="/filterListSuppliers", method = RequestMethod.GET)
    public @ResponseBody List<Supplier> filterListSuppliers(@RequestParam String word){
        return supplierService.filterListSuppliers(word);
    }

    /**Método para buscar um fornecedor
     * @return Supplier - Fornecedor
     */
    @RequestMapping(value="/listSupplierById", method = RequestMethod.GET)
    public @ResponseBody Supplier listSupplierById(@RequestParam Long id){
        return supplierService.listSupplierById(id);
    }

    /**Método para adicionar um fornecedor
     */
    @RequestMapping(value="/addSupplier", method = RequestMethod.POST)
    public void addSupplier(@RequestBody CreateSupplierRequest createSupplierRequest){
        supplierService.addNewSupplier(createSupplierRequest);
    }

    /**Método para editar um fornecedor
     */
    @RequestMapping(value="/editSupplier", method = RequestMethod.POST)
    public void editSupplier(@RequestBody EditSupplierRequest editSupplierRequest){
        supplierService.editSupplierById(editSupplierRequest);
    }
}
