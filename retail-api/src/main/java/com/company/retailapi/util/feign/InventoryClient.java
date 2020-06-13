package com.company.retailapi.util.feign;

import com.company.retailapi.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET)
    Inventory getInventory(@PathVariable int id);
}
