package com.company.customerservice.controller;

import com.company.customerservice.service.ServiceLayer;
import com.company.customerservice.viewModels.CustomerView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ServiceLayer service;

    CustomerView fromDB;
    CustomerView customerToCreate;
    List<CustomerView> customerViewList;

    // Mapper to turn Java objects into JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        // setup test objects
        customerToCreate = new CustomerView();
        customerToCreate.setFirstName("fname");
        customerToCreate.setLastName("lNAme");
        customerToCreate.setCity("City");
        customerToCreate.setEmail("email");
        customerToCreate.setPhone("987654321");
        customerToCreate.setStreet("street");
        customerToCreate.setZip("07213");

        //product from DB
        fromDB = new CustomerView();
        fromDB.setId(1);
        fromDB.setFirstName("fname");
        fromDB.setLastName("lNAme");
        fromDB.setCity("City");
        fromDB.setEmail("email");
        fromDB.setPhone("987654321");
        fromDB.setStreet("street");
        fromDB.setZip("07213");

        customerViewList = new ArrayList<>();
        customerViewList.add(fromDB);

        // fire up
        setUpServiceMock();
    }

    @Test
    public void shouldAddCustomer() throws Exception {
        //Assemble
        String inputJson = mapper.writeValueAsString(customerToCreate);
        String outputJson = mapper.writeValueAsString(fromDB);
        System.out.println(inputJson);
        System.out.println(outputJson);
        //Act
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON))
                //Assert
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldThrow422WhenEmptyFields() throws Exception {
        //Assemble
        CustomerView invalid = new CustomerView();

        String inputJson = mapper.writeValueAsString(invalid);


        //Act
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON))
                //Assert
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldGetAllCustomers()  throws Exception {
        String outputJson = mapper.writeValueAsString(customerViewList);

        //Act
        mockMvc.perform(get("/customers"))
                //Assert
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetCustomer() throws Exception {
        String outputJson = mapper.writeValueAsString(fromDB);

        //Act
        mockMvc.perform(get("/customers/1"))
                //Assert
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn404WhenNotFound() throws Exception {
        //Act
        mockMvc.perform(get("/customers/100"))
                //Assert
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        //Assemble
        String inputJson = mapper.writeValueAsString(fromDB);
        //Act
        mockMvc.perform(put("/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON))
                //Assert
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn422WhenUpdatingWithInvalidId() throws Exception {
        //Assemble
        String inputJson = mapper.writeValueAsString(fromDB);
        //Act
        mockMvc.perform(put("/customers/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON))
                //Assert
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        //Act
        mockMvc.perform(delete("/customers/1"))
                //Assert
                .andExpect(status().isNoContent());
    }
// forgot to incorporate NotFoundException in deleteProduct method of controller.
// Will incorporate later.

//    @Test
//    public void shouldReturn404WhenDeletingNonExistingProduct() throws Exception {
//        //Act
//        mockMvc.perform(delete("/products/100"))
//                //Assert
//                .andExpect(status().isNotFound());
//    }

    // helper method
    private void setUpServiceMock() {
        doReturn(fromDB).when(service).newCustomer(customerToCreate);
        doReturn(fromDB).when(service).fetchCustomer(1);
        doReturn(customerViewList).when(service).fetchAllCustomers();
    }
}