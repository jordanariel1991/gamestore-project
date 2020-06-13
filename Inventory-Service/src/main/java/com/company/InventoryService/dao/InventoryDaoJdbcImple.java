package com.company.InventoryService.dao;


import com.company.InventoryService.models.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryDaoJdbcImple implements InventoryDao {



    private  static final String INSERT_INVENTORY =
            "INSERT INTO inventory(product_id,quantity) VALUES(?,?)";
    private static final String FIND_INVENTORY_BY_ID =
            "SELECT * from inventory WHERE inventory_id =?";
    private static final String FIND_ALL_INVENTORY =
            "SELECT * from  inventory";
    private static final String DELETE_INVENTORY =
            "DELETE FROM inventory WHERE inventory_id =?";
    private static final String UPDATE_INVENTORY =
            "UPDATE inventory SET product_id =?,quantity =? where inventory_id = ?";

    private static final String SELECT_LAST_INSERT_ID ="SELECT LAST_INSERT_ID()";


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Inventory findInventoryById(int id) {
        try{
            return jdbcTemplate.queryForObject(FIND_INVENTORY_BY_ID,this::mapper, id);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Inventory saveInventory(Inventory inventory) {
        jdbcTemplate.update(INSERT_INVENTORY,
                inventory.getProductId(),
                inventory.getQuantity()
        );
        int id=jdbcTemplate.queryForObject(SELECT_LAST_INSERT_ID,Integer.class);
        inventory.setInventoryId(id);
        return inventory;
    }

    @Override
    public List<Inventory> FindAllInventory() {
        return jdbcTemplate.query(FIND_ALL_INVENTORY,this::mapper);
    }

    @Override
    public void deleteInventoryById(int id) {
        jdbcTemplate.update(DELETE_INVENTORY,id);

    }

    @Override
    public void updateInventory(Inventory inventory) {
        jdbcTemplate.update(UPDATE_INVENTORY,

                inventory.getProductId(),
                inventory.getQuantity(),
                inventory.getInventoryId());

    }

    //helper method
    private Inventory mapper (ResultSet rs, int rowNum) throws SQLException{
        Inventory inventory = new Inventory();
        inventory.setProductId(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));
        inventory.setInventoryId(rs.getInt("inventory_id"));

        return inventory;
    }
}
