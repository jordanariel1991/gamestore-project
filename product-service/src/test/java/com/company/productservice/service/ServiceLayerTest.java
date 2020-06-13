package com.company.productservice.service;

import com.company.productservice.DAO.ProductDao;
import com.company.productservice.DAO.ProductDaoJdbcImpl;
import com.company.productservice.DTO.Product;
import com.company.productservice.viewModels.ProductView;
import org.junit.Before;
import org.junit.Test;
import sun.plugin.perf.PluginRollup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer serviceLayer;
    ProductDao dao;

    @Before
    public void setUp() throws Exception {
        setUpProductDaoMock();
        serviceLayer = new ServiceLayer(dao);
    }

    private void setUpProductDaoMock(){
        dao = mock(ProductDaoJdbcImpl.class);

        //product to create
        Product productToCreate = new Product();
        productToCreate.setName("Product");
        productToCreate.setDescription("Desc");
        productToCreate.setListPrice(new BigDecimal("19.99").setScale(2));
        productToCreate.setUnitCost(new BigDecimal("4.99").setScale(2));

        //product from DB
        Product fromDB = new Product();
        fromDB.setId(1);
        fromDB.setName("Product");
        fromDB.setDescription("Desc");
        fromDB.setListPrice(new BigDecimal("19.99").setScale(2));
        fromDB.setUnitCost(new BigDecimal("4.99").setScale(2));

        //All Products
        List<Product> productList = new ArrayList<>();
        productList.add(fromDB);

        doReturn(fromDB).when(dao).addProduct(productToCreate);
        doReturn(fromDB).when(dao).getProduct(1);
        doReturn(productList).when(dao).getAll();

    }

    @Test
    public void shouldCreateAndFetchProduct() {
        ProductView productView = new ProductView();
        productView.setName("Product");
        productView.setDescription("Desc");
        productView.setListPrice(new BigDecimal("19.99").setScale(2));
        productView.setUnitCost(new BigDecimal("4.99").setScale(2));

        productView = serviceLayer.newProduct(productView);
        ProductView productView1 = serviceLayer.fetchProduct(productView.getId());
        assertEquals(productView1, productView);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfEmptyFields() {
        serviceLayer.newProduct(new ProductView());
    }

    @Test
    public void fetchAllProducts() {
        ProductView productView = new ProductView();
        productView.setName("Product");
        productView.setDescription("Desc");
        productView.setListPrice(new BigDecimal("19.99").setScale(2));
        productView.setUnitCost(new BigDecimal("4.99").setScale(2));
        productView = serviceLayer.newProduct(productView);

        List<ProductView> productViewList = serviceLayer.fetchAllProducts();
        assertEquals(productViewList.size(), 1);
    }


}