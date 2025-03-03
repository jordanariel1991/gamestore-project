package com.company.adminapi.controller;

import com.company.adminapi.exception.NotFoundException;
import com.company.adminapi.service.ServiceLayer;
import com.company.adminapi.views.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductView createProduct(@RequestBody @Valid ProductView productView) {
        return serviceLayer.newProduct(productView);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductView getById(@PathVariable int id) {
        ProductView productView;
        productView = serviceLayer.fetchProduct(id);
        if (productView == null) throw new NotFoundException("No Product matches this ID" + id);

        return productView;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductView> getAllProducts() {
        return serviceLayer.fetchAllProducts();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateProduct(@PathVariable int id, @RequestBody @Valid ProductView productView) {
        if (productView.getId() != id) {
            throw new IllegalArgumentException("id in path must match id in request body");
        }
        ProductView toUpdate = serviceLayer.fetchProduct(productView.getId());
        if (toUpdate == null) throw new NotFoundException("no product matches the given id " + id);
        serviceLayer.updateProduct(id, productView);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id){
        serviceLayer.deleteProduct(id);
    }


}
