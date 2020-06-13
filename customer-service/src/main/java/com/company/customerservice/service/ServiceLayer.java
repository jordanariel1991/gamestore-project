package com.company.customerservice.service;

import com.company.customerservice.DAO.CustomerDao;
import com.company.customerservice.DTO.Customer;
import com.company.customerservice.viewModels.CustomerView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {
    private CustomerDao customerDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public CustomerView newCustomer(CustomerView customerView) {
        Customer customer = new Customer();

        customer.setFirstName(customerView.getFirstName());
        customer.setLastName(customerView.getLastName());
        customer.setCity(customerView.getCity());
        customer.setEmail(customerView.getEmail());
        customer.setPhone(customerView.getPhone());
        customer.setStreet(customerView.getStreet());
        customer.setZip(customerView.getZip());

        customer = customerDao.addCustomer(customer);

        customerView.setId(customer.getId());


        return customerView;

    }

    public CustomerView fetchCustomer(int id) {
        Customer customer = customerDao.getCustomer(id);
        if (customer == null) return null;

        CustomerView customerView = new CustomerView();
        customerView.setId(customer.getId());
        customerView.setFirstName(customer.getFirstName());
        customerView.setLastName(customer.getLastName());
        customerView.setCity(customer.getCity());
        customerView.setEmail(customer.getEmail());
        customerView.setPhone(customer.getPhone());
        customerView.setStreet(customer.getStreet());
        customerView.setZip(customer.getZip());

        return customerView;

    }

    public List<CustomerView> fetchAllCustomers() {
        List<Customer> customerList = customerDao.getAll();
        if (customerList.isEmpty() || customerList == null) return null;

        List<CustomerView> customerViewList = new ArrayList<>();
        customerList.stream().forEach(customer -> {
            CustomerView customerView = new CustomerView();
            customerView.setId(customer.getId());
            customerView.setFirstName(customer.getFirstName());
            customerView.setLastName(customer.getLastName());
            customerView.setCity(customer.getCity());
            customerView.setEmail(customer.getEmail());
            customerView.setPhone(customer.getPhone());
            customerView.setStreet(customer.getStreet());
            customerView.setZip(customer.getZip());

            customerViewList.add(customerView);

        });

        return customerViewList;
    }

    public void updateCustomer(CustomerView customerView) {
        Customer customer = new Customer();
        customerView.setFirstName(customer.getFirstName());
        customerView.setLastName(customer.getLastName());
        customerView.setCity(customer.getCity());
        customerView.setEmail(customer.getEmail());
        customerView.setPhone(customer.getPhone());
        customerView.setStreet(customer.getStreet());
        customerView.setZip(customer.getZip());
        customerDao.updateCustomer(customer);

    }

    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }
}
