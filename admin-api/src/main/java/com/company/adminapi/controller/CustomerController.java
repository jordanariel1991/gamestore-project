package com.company.adminapi.controller;

import com.company.adminapi.exception.NotFoundException;
import com.company.adminapi.service.ServiceLayer;
import com.company.adminapi.views.CustomerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerView createCustomer(@RequestBody @Valid CustomerView customerView) {
        return serviceLayer.newCustomer(customerView);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerView getById(@PathVariable int id) {
        CustomerView customerView;
        customerView = serviceLayer.fetchCustomer(id);
        if (customerView == null) throw new NotFoundException("No customer matches this ID" + id);

        return customerView;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerView> getAllCustomers() {
        return serviceLayer.fetchAllCustomers();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCustomer(@PathVariable int id, @RequestBody @Valid CustomerView customerView) {
        if (customerView.getId() != id) {
            throw new IllegalArgumentException("id in path must match id in request body");
        }
        CustomerView toUpdate = serviceLayer.fetchCustomer(customerView.getId());
        if (toUpdate == null) throw new NotFoundException("no customer matches the given id " + id);
        serviceLayer.updateCustomer(id,customerView);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id){
        serviceLayer.deleteCustomer(id);
    }


}
