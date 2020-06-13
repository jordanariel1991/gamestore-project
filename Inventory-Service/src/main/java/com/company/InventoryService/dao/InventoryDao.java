package com.company.InventoryService.dao;

import com.company.InventoryService.models.Inventory;

import java.util.List;

public interface InventoryDao {

    Inventory findInventoryById( int id);

    Inventory saveInventory( Inventory inventory);

    List<Inventory> FindAllInventory ();

    void deleteInventoryById(int id);

    void updateInventory(Inventory inventory);

}
