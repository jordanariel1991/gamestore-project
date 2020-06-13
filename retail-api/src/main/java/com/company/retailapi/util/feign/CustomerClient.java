package com.company.retailapi.util.feign;

import com.company.retailapi.viewModel.CustomerView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="customer-service")
public interface CustomerClient {

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    CustomerView getCustomer(@PathVariable int id);
}
