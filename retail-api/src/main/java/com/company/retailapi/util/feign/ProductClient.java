package com.company.retailapi.util.feign;

import com.company.retailapi.viewModel.ProductView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @RequestMapping(value = "/products/inventory", method = RequestMethod.GET)
    public List<ProductView> getProductsInInventory();

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductView getProductById(@PathVariable int id);


    @RequestMapping(value = "/products/invoice/{id}", method = RequestMethod.GET)
    public List<ProductView> getProductByInvoiceId(@PathVariable int id);

}
