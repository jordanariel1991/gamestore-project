package com.company.customerservice.service;

import com.company.customerservice.DAO.CustomerDao;
import com.company.customerservice.DAO.CustomerDaoJdbcImpl;
import com.company.customerservice.DTO.Customer;
import com.company.customerservice.viewModels.CustomerView;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    ServiceLayer serviceLayer;
    CustomerDao dao;

    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        serviceLayer = new ServiceLayer(dao);
    }

    private void setUpCustomerDaoMock(){
        dao = mock(CustomerDaoJdbcImpl.class);

        //product to create
        Customer customerToCreate = new Customer();

        customerToCreate.setFirstName("fname");
        customerToCreate.setLastName("lNAme");
        customerToCreate.setCity("City");
        customerToCreate.setEmail("email");
        customerToCreate.setPhone("987654321");
        customerToCreate.setStreet("street");
        customerToCreate.setZip("07213");

        //product from DB
        Customer fromDB = new Customer();
        fromDB.setId(1);
        fromDB.setFirstName("fname");
        fromDB.setLastName("lNAme");
        fromDB.setCity("City");
        fromDB.setEmail("email");
        fromDB.setPhone("987654321");
        fromDB.setStreet("street");
        fromDB.setZip("07213");

        //All Products
        List<Customer> customerList = new ArrayList<>();
        customerList.add(fromDB);

        doReturn(fromDB).when(dao).addCustomer(customerToCreate);
        doReturn(fromDB).when(dao).getCustomer(1);
        doReturn(customerList).when(dao).getAll();

    }

    @Test
    public void shouldCreateAndFetchCustomer() {
        CustomerView customerView = new CustomerView();

        customerView.setFirstName("fname");
        customerView.setLastName("lNAme");
        customerView.setCity("City");
        customerView.setEmail("email");
        customerView.setPhone("987654321");
        customerView.setStreet("street");
        customerView.setZip("07213");

        customerView = serviceLayer.newCustomer(customerView);
        CustomerView customerView1 = serviceLayer.fetchCustomer(customerView.getId());
        assertEquals(customerView1, customerView);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfEmptyFields() {
        serviceLayer.newCustomer(new CustomerView());
    }

    @Test
    public void fetchAllCustomer() {
        CustomerView customerView = new CustomerView();
        customerView.setFirstName("fname");
        customerView.setLastName("lNAme");
        customerView.setCity("City");
        customerView.setEmail("email");
        customerView.setPhone("987654321");
        customerView.setStreet("street");
        customerView.setZip("07213");
        customerView = serviceLayer.newCustomer(customerView);

        List<CustomerView> customerViewList = serviceLayer.fetchAllCustomers();
        assertEquals(customerViewList.size(), 1);
    }



}