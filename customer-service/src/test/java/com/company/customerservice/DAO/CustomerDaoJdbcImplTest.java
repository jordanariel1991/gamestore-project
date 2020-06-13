package com.company.customerservice.DAO;

import com.company.customerservice.DTO.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ROUND_CEILING;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CustomerDaoJdbcImplTest {
    @Autowired
    CustomerDao dao;

    Customer customer;
    List<Customer> customerList;


    //I am using Junit4, yet my tests only pass if I use BeforeEach. which is a Junit5 thing. why?
    @BeforeEach
    public void setUp() throws Exception {
        //instanatiate test obj

        customer = new Customer();
        customer.setFirstName("fName");
        customer.setLastName("lastName");
        customer.setStreet("street");
        customer.setCity("city");
        customer.setZip("17013");
        customer.setEmail("email");
        customer.setPhone("123456879");


        //clear database
        customerList = dao.getAll();
        for (Customer c : customerList) {
            dao.deleteCustomer(c.getId());
        }


    }

    @Test
    void shouldAddGetDeleteProduct() {
        //add
        customer = dao.addCustomer(customer);
        //get
        Customer customer1 = dao.getCustomer(customer.getId());

        assertEquals(customer1, customer);
        //delete
        dao.deleteCustomer(customer.getId());
        customer1 = dao.getCustomer(customer.getId());
        assertNull(customer1);

    }


    @Test
    void shouldGetAll() {

//        customer_id int(11) not null auto_increment primary key,
//                first_name varchar(50) not null,
//                last_name varchar(50) not null,
//                street varchar(50) not null,
//                city varchar(50) not null,
//                zip varchar(10) not null,
//                email varchar(75) not null,
//                phone varchar(20) not null

        customer = dao.addCustomer(customer);
        customer = new Customer();
        customer.setFirstName("fName");
        customer.setLastName("lastName");
        customer.setStreet("street");
        customer.setCity("city");
        customer.setZip("17013");
        customer.setEmail("email");
        customer.setPhone("123456879");



        customer = dao.addCustomer(customer);

        customerList = dao.getAll();

        assertEquals(customerList.size(), 2);


    }

    @Test
    void shouldUpdateProduct() {


        customer = dao.addCustomer(customer);

        customer.setFirstName("fName");
        customer.setLastName("lastName");
        customer.setStreet("street");
        customer.setCity("city");
        customer.setZip("17013");
        customer.setEmail("email");
        customer.setPhone("123456879");

        customer = dao.addCustomer(customer);




       Customer customer1 = dao.getCustomer(customer.getId());
        assertEquals(customer1, customer);
    }


}