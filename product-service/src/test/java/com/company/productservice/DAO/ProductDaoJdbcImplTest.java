package com.company.productservice.DAO;

import com.company.productservice.DTO.Product;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ROUND_CEILING;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ProductDaoJdbcImplTest {

    @Autowired
    ProductDao dao;

    Product product;
    List<Product> productList;


    //I am using Junit4, yet my tests only pass if I use BeforeEach. which is a Junit5 thing. why?
    @BeforeEach
    public  void setUp() throws Exception{
        //instanatiate test obj
        product = new Product();
        product.setDescription("cool toy");
        product.setListPrice(new BigDecimal(19.99).setScale(2, ROUND_CEILING));
        product.setName("Mario plus");
        product.setUnitCost(new BigDecimal(9.99).setScale(2, ROUND_CEILING));

        //clear database
        productList = dao.getAll();
        for (Product p : productList) {
            dao.deleteProduct(p.getId());
        }


    }
    @Test
    void shouldAddGetDeleteProduct() {
        //add
        product = dao.addProduct(product);
        //get
        Product product1 = dao.getProduct(product.getId());

        assertEquals(product1, product);
        //delete
        dao.deleteProduct(product.getId());
        product1 = dao.getProduct(product.getId());
        assertNull(product1);

    }


    @Test
    void shouldGetAll() {
        product = dao.addProduct(product);

        product = new Product();
        product.setDescription("mediocre toy");
        product.setListPrice(new BigDecimal(29.99).setScale(2, ROUND_CEILING));
        product.setName("Bowser plush");
        product.setUnitCost(new BigDecimal(9.99).setScale(2, ROUND_CEILING));

        product = dao.addProduct(product);

        productList = dao.getAll();

        assertEquals(productList.size(), 2);


    }

    @Test
    void shouldUpdateProduct() {

        product = dao.addProduct(product);
        product.setDescription("really fun toy");
        product.setListPrice(new BigDecimal(29.99).setScale(2, ROUND_CEILING));
        product.setName("Bowser plush");
        product.setUnitCost(new BigDecimal(9.99).setScale(2, ROUND_CEILING));
        product = dao.addProduct(product);
        Product product1 = dao.getProduct(product.getId());
        assertEquals(product1, product);
    }


}