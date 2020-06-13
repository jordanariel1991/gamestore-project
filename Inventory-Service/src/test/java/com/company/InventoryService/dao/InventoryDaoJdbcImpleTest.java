package com.company.InventoryService.dao;

import com.company.InventoryService.models.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InventoryDaoJdbcImpleTest {

    @Autowired
    InventoryDao inventoryDao;

    @Before
    public void setUp() throws Exception {
        inventoryDao.FindAllInventory().forEach(inventory -> inventoryDao.deleteInventoryById(inventory.getInventoryId()));
    }

    @Test
    public void shouldAddGetDeleteInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(2);


        inventory =inventoryDao.saveInventory(inventory);
        assertEquals(inventory,inventoryDao.findInventoryById(inventory.getInventoryId()));

        inventoryDao.deleteInventoryById(inventory.getInventoryId());
        assertNull(inventoryDao.findInventoryById(inventory.getInventoryId()));
    }

    @Test
    public void shouldGetAllInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(2);

        inventory =inventoryDao.saveInventory(inventory);
        assertEquals(1,inventoryDao.FindAllInventory().size());

    }


    @Test
    public void shouldUpdateInventoryById() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(2);

        inventory =inventoryDao.saveInventory(inventory);

        inventory.setQuantity(3);
        inventoryDao.updateInventory(inventory);
        assertEquals(inventoryDao.findInventoryById(inventory.getInventoryId()), inventory);


    }
}