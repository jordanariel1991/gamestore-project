package com.company.Invoiceservice.controller;


import com.company.Invoiceservice.serviceLayer.InvoiceServiceLayer;
import com.company.Invoiceservice.viewmodels.InvoiceVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CacheConfig(cacheNames = "{invoice}")
//@RefreshScope
@RequestMapping(value = "/invoice")
public class InvoiceController {

    @Autowired
    InvoiceServiceLayer invoiceServiceLayer;


    @GetMapping(value = "/customer/{customerId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceVM> getInvByCust(@PathVariable int custId){
        return invoiceServiceLayer.getInvByCustId(custId);
    }

    @CacheEvict
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInv(@PathVariable int invId){
        invoiceServiceLayer.deleteInvoice(invId);
    }

    @CachePut
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceVM addInvoice(@RequestBody @Valid InvoiceVM ivm){
        return invoiceServiceLayer.addInvoice(ivm);
    }
    @CacheEvict(key = "#id")
    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceVM updateInv(@RequestBody @Valid InvoiceVM ivm, @PathVariable int invId) {
        if(invId!=ivm.getInvoiceId()){
            throw new IllegalArgumentException("InvId has to match the request body");
        }
        return invoiceServiceLayer.updateInv(ivm);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public  List<InvoiceVM>getAllInv(){
        return invoiceServiceLayer.getAllInv();
    }
    @Cacheable
    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceVM getInvoice(@PathVariable int invId){
        return invoiceServiceLayer.getInv(invId);
    }
}
