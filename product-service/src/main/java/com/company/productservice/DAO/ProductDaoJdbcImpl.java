package com.company.productservice.DAO;

import com.company.productservice.DTO.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoJdbcImpl implements ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_PRODUCT =
            "insert into product (product_name, product_description, list_price, unit_cost) values (?, ?, ?, ?)";
    private static final String SELECT_PRODUCT_BY_ID =
            "select * from product where product_id =?";
    private static final String UPDATE_PRODUCT =
            "update product set product_name = ?, product_description = ?,  list_price = ?, unit_cost = ?, where product_id = ?";
    private static final String DELETE_PRODUCT =
            "delete from product where product_id = ?";
    private static final String SELECT_All_PRODUCT =
            "select * from product";
    @Transactional
    @Override
    public Product addProduct(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT,
                product.getName(),
                product.getDescription(),
                product.getListPrice(),
                product.getUnitCost());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        product.setId(id);
        return product;
    }

    @Override
    public Product getProduct(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID, this::mapRowToProduct, id);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query(SELECT_All_PRODUCT, this::mapRowToProduct);
    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT,
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getListPrice(),
                product.getUnitCost()

        );

    }

    @Override
    public void deleteProduct(int id) {
        jdbcTemplate.update(DELETE_PRODUCT, id);
    }

    private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setName(rs.getString("product_name"));
        product.setDescription(rs.getString("product_description"));
        product.setListPrice(rs.getBigDecimal("list_price"));
        product.setUnitCost(rs.getBigDecimal("unit_cost"));

        return product;
    }
}
