package com.company.customerservice.DAO;

import com.company.customerservice.DTO.Customer;

import java.util.List;

public interface CustomerDao {
    Customer addCustomer(Customer customer);
    Customer getCustomer(int id);
    List<Customer> getAll();
    void updateCustomer(Customer customer);
    void deleteCustomer (int id);
}
