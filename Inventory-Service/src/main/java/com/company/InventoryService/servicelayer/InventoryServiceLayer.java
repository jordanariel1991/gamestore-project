package com.company.InventoryService.servicelayer;


import com.company.InventoryService.dao.InventoryDao;
import com.company.InventoryService.exceptions.NotFoundException;
import com.company.InventoryService.models.Inventory;
import com.company.InventoryService.viewmodel.InventoryViewmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryServiceLayer {

    @Autowired
    InventoryDao inventoryDao;


    @Autowired
    public InventoryServiceLayer(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public InventoryViewmodel addInventory(InventoryViewmodel inventoryViewmodel) {
        Inventory inventory = new Inventory();
        inventory.setProductId(inventoryViewmodel.getProductId());
        inventory.setQuantity(inventoryViewmodel.getQuantity());


        inventory = inventoryDao.saveInventory(inventory);

        inventoryViewmodel.setInventoryId(inventory.getInventoryId());
        return inventoryViewmodel;
    }

    public void updateInventory(InventoryViewmodel inventoryViewmodel) {

        Inventory inventory = new Inventory();
        inventory.setProductId(inventoryViewmodel.getProductId());
        inventory.setQuantity(inventoryViewmodel.getQuantity());
        inventoryDao.updateInventory(inventory);
    }

    public InventoryViewmodel findInventoryById(int id) {
        Inventory inventory= inventoryDao.findInventoryById(id);
        if (inventory == null)
            throw new NotFoundException("not in db");
        else
            return buildInventory(inventory);
    }

    public List<InventoryViewmodel> getAllInventory() {
        return inventoryDao.FindAllInventory().stream().map(this::buildInventory).collect(Collectors.toList());
    }

    public void deleteInventory(int id) {
        inventoryDao.deleteInventoryById(id);
    }


    private InventoryViewmodel buildInventory(Inventory inventory) {
        InventoryViewmodel viewModel = new InventoryViewmodel();
        viewModel.setInventoryId(inventory.getInventoryId());
        viewModel.setProductId(inventory.getProductId());
        viewModel.setQuantity(inventory.getQuantity());


        return viewModel;


    }
}





