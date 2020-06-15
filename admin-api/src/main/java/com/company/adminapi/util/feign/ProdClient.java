package com.company.adminapi.util.feign;

import com.company.adminapi.views.ProductView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "product-service")
public interface ProdClient {

    @RequestMapping(value = "/products/inventory", method = RequestMethod.GET)
    public List<ProductView> getProductsInInventory();

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductView getProductById(@PathVariable int id);


    @RequestMapping(value = "/products/invoice/{id}", method = RequestMethod.GET)
    public List<ProductView> getProductByInvoiceId(@PathVariable int id);

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductView createProduct(@RequestBody @Valid ProductView productView);

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductView getById(@PathVariable int id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductView> getAllProducts();

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateProduct(@PathVariable int id, @RequestBody @Valid ProductView productView);


}