package com.company.productservice.DAO;

import com.company.productservice.DTO.Product;


import java.util.List;

public interface ProductDao {
    Product addProduct(Product product);
    Product getProduct(int id);
    List<Product>getAll();
    void updateProduct(Product product);
    void deleteProduct (int id);

}
