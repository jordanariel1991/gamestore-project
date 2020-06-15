package com.company.adminapi.util.feign;

import com.company.adminapi.views.InventoryView;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    //these should work, but may need to resort to requestmapping



    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public InventoryView addInventory(@RequestBody @Valid InventoryView inventoryView);


    @GetMapping("/{id}")//Another way to set the Rest API Get mapping
    @ResponseStatus(HttpStatus.OK)
    public InventoryView getInventoryById(@PathVariable("id") int inventoryId);


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryView> getAllInventory();



    @DeleteMapping("/{id}")//Another way to set the Rest API Delete mapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventory(@PathVariable("id") int inventoryId);



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInventory(@PathVariable("id") int inventoryId, @RequestBody @Valid InventoryView inventoryView);

}



