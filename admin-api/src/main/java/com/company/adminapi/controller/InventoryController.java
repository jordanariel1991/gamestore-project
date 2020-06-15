package com.company.adminapi.controller;

import com.company.adminapi.exception.NotFoundException;
import com.company.adminapi.service.ServiceLayer;
import com.company.adminapi.views.InventoryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public InventoryView addInventory(@RequestBody @Valid InventoryView inventoryView) {
        return serviceLayer.newInventory(inventoryView);
    }

    @GetMapping("/{id}")//Another way to set the Rest API Get mapping
    @ResponseStatus(HttpStatus.OK)
    public InventoryView getInventoryById(@PathVariable("id") int id) {
        InventoryView inventoryView = serviceLayer.findInventoryById(id);
        if (inventoryView == null)
            throw new NotFoundException("console could not be retrieved for id " + id);
        return inventoryView;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryView> getAllInventory(){
        return serviceLayer.fetchAllInventory();
    }


    @DeleteMapping("/{id}")//Another way to set the Rest API Delete mapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventory(@PathVariable("id") int id) {
        serviceLayer.deleteInventory(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInventory(@PathVariable("id") int id, @RequestBody @Valid InventoryView inventoryView ) {
        if (inventoryView.getInventoryId()==0)
            inventoryView.setInventoryId(id);
        if(id != inventoryView.getInventoryId()) {
            throw new IllegalArgumentException("Inventory ID on path must match the ID in the  object");

        }
        serviceLayer.updateInventory(id, inventoryView);
    }
}

