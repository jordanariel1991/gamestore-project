package com.company.InventoryService.servicelayer;

import com.company.InventoryService.dao.InventoryDao;
import com.company.InventoryService.dao.InventoryDaoJdbcImple;
import com.company.InventoryService.models.Inventory;
import com.company.InventoryService.viewmodel.InventoryViewmodel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.Silent.class)
public class InventoryServiceLayerTest {
    @Autowired
    InventoryServiceLayer inventoryServiceLayer;

    InventoryDao inventoryDao;


    @Before
    public void setUp() throws Exception {
        setUpInventoryDaoMock();


        inventoryServiceLayer = new InventoryServiceLayer(inventoryDao);
    }

    private void setUpInventoryDaoMock(){
       inventoryDao = mock(InventoryDaoJdbcImple.class);

        Inventory inventoryDB = new Inventory();
        inventoryDB.setInventoryId(1);
        inventoryDB.setProductId(2);
        inventoryDB.setQuantity(3);


        Inventory inventorytoCreate = new Inventory();
        inventorytoCreate.setProductId(2);
        inventorytoCreate.setQuantity(3);

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventoryDB);

        doReturn(inventoryDB).when(inventoryDao).saveInventory(inventorytoCreate);
        doReturn(inventoryDB).when(inventoryDao).findInventoryById(1);
        doReturn(inventoryList).when(inventoryDao).FindAllInventory();


    }

    @Test
    public void shouldAddandGetInventory() {
        InventoryViewmodel inventory = new InventoryViewmodel();
        inventory.setInventoryId(1);
        inventory.setProductId(2);
        inventory.setQuantity(3);


        inventory =inventoryServiceLayer.addInventory(inventory);
       InventoryViewmodel fromService = inventoryServiceLayer.findInventoryById(inventory.getInventoryId());
        assertEquals(inventory, fromService);
    }


    @Test
    public void getAllInventory() {
        InventoryViewmodel inventory = new InventoryViewmodel();
        inventory.setInventoryId(1);
        inventory.setProductId(2);
        inventory.setQuantity(3);


        inventory =inventoryServiceLayer.addInventory(inventory);
        assertEquals(1,inventoryDao.FindAllInventory().size());
    }


}