package com.company.adminapi.controller;


import com.company.adminapi.service.ServiceLayer;
import com.company.adminapi.views.InvoiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//import org.springframework.cloud.context.config.annotation.RefreshScope;

@RestController

@RefreshScope
@RequestMapping(value = "/invoice")
public class InvoiceController {

    @Autowired
    ServiceLayer serviceLayer;


    @GetMapping(value = "/customer/{customerId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceView> getInvByCust(@PathVariable int custId){
        return serviceLayer.getInvoiceByCustId(custId);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInv(@PathVariable int id){
        serviceLayer.deleteInvoice(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceView addInvoice(@RequestBody @Valid InvoiceView invoiceView){
        return serviceLayer.addInvoice(invoiceView);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceView updateInv(@RequestBody @Valid InvoiceView invoiceView, @PathVariable int id) {
        if(id!=invoiceView.getId()){
            throw new IllegalArgumentException("InvId has to match the request body");
        }
        return serviceLayer.updateInvoice(invoiceView);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public  List<InvoiceView>getAllInv(){
        return serviceLayer.getAllInvoices();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceView getInvoice(@PathVariable int id){
        return serviceLayer.getInvoice(id);
    }
}
