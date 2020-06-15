package com.company.adminapi.util.feign;

import com.company.adminapi.views.CustomerView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "customer-service")
public interface CustClient {

    // this should work with {method mapping}, may need to change to @RequestMapping if feign doesn't support it like this.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerView createCustomer(@RequestBody @Valid CustomerView customerView);

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerView getById(@PathVariable int id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerView> getAllCustomers();

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCustomer(@PathVariable int id, @RequestBody @Valid CustomerView customerView);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id);




}
