package com.company.InventoryService.controller;

import com.company.InventoryService.exceptions.NotFoundException;
import com.company.InventoryService.models.Inventory;
import com.company.InventoryService.servicelayer.InventoryServiceLayer;
import com.company.InventoryService.viewmodel.InventoryViewmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    InventoryServiceLayer inventoryServiceLayer;


    @CachePut(key = "#result.getInventoryId()")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public InventoryViewmodel addInventory(@RequestBody @Valid InventoryViewmodel inventoryVm) {
        return inventoryServiceLayer.addInventory(inventoryVm);
    }
    @Cacheable
    @GetMapping("/{id}")//Another way to set the Rest API Get mapping
    @ResponseStatus(HttpStatus.OK)
    public InventoryViewmodel getinventoryByid(@PathVariable("id") int inventoryId) {
        InventoryViewmodel inventoryVM = inventoryServiceLayer.findInventoryById(inventoryId);
        if (inventoryVM == null)
            throw new NotFoundException("console could not be retrieved for id " + inventoryId);
        return inventoryVM;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryViewmodel> getAllInventory(){
        return inventoryServiceLayer.getAllInventory();
    }

    @CacheEvict
    @DeleteMapping("/{id}")//Another way to set the Rest API Delete mapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventory(@PathVariable("id") int inventoryId) {
        inventoryServiceLayer.deleteInventory(inventoryId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateinventory(@PathVariable("id") int inventoryId, @RequestBody @Valid InventoryViewmodel inventoryViewmodel ) {
        if (inventoryViewmodel.getInventoryId()==0)
            inventoryViewmodel.setInventoryId(inventoryId);
        if(inventoryId != inventoryViewmodel.getInventoryId()) {
            throw new IllegalArgumentException("Inventory ID on path must match the ID in the  object");

        }
        inventoryServiceLayer.updateInventory(inventoryViewmodel);
    }
}

