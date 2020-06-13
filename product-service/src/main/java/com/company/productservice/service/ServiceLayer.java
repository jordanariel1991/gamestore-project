package com.company.productservice.service;

import com.company.productservice.DAO.ProductDao;
import com.company.productservice.DTO.Product;
import com.company.productservice.viewModels.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {
    private ProductDao productDao;

    @Autowired
    public ServiceLayer(ProductDao productDao) {
        this.productDao = productDao;
    }
    public ProductView newProduct(ProductView productView){
        Product product = new Product();
        product.setUnitCost(productView.getUnitCost());
        product.setDescription(productView.getDescription());
        product.setName(productView.getName());
        product.setListPrice(productView.getListPrice());
        product = productDao.addProduct(product);

        productView.setId(product.getId());


        return productView;

    }

    public ProductView fetchProduct(int id){
        Product product = productDao.getProduct(id);
        if (product == null) return null;

        ProductView productView = new ProductView();
        productView.setId(product.getId());
        productView.setUnitCost(product.getUnitCost());
        productView.setDescription(product.getDescription());
        productView.setName(product.getName());
        productView.setListPrice(product.getListPrice());

        return productView;

    }

    public List<ProductView> fetchAllProducts() {
        List<Product> productList = productDao.getAll();
        if (productList.isEmpty() || productList == null) return null;

        List<ProductView> productViewList = new ArrayList<>();
        productList.stream() .forEach(product -> {
            ProductView productView = new ProductView();
            productView.setId(product.getId());
            productView.setUnitCost(product.getUnitCost());
            productView.setDescription(product.getDescription());
            productView.setName(product.getName());
            productView.setListPrice(product.getListPrice());

            productViewList.add(productView);

        });

        return productViewList;
    }

    public void updateProduct(ProductView productView){
        Product product = new Product();
        product.setUnitCost(productView.getUnitCost());
        product.setDescription(productView.getDescription());
        product.setName(productView.getName());
        product.setListPrice(productView.getListPrice());
        productDao.updateProduct(product);

    }

    public void deleteProduct(int id){
        productDao.deleteProduct(id);
    }
}
