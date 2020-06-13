package com.company.customerservice.DAO;

import com.company.customerservice.DTO.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoJdbcImpl implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_CUSTOMER =
            "insert into customer (first_name, last_name, street, city, zip, email, phone) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_CUSTOMER_BY_ID =
            "select * from customer where customer_id =?";
    private static final String UPDATE_CUSTOMER =
            "update customer set first_name = ?, last_name = ?, street = ?, city = ?, zip = ?, email = ?, phone = ?, where customer_id = ?";
    private static final String DELETE_CUSTOMER =
            "delete from customer where customer_id = ?";
    private static final String SELECT_All_CUSTOMER =
            "select * from customer";

    @Transactional
    @Override
    public Customer addCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone()

                );


        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        customer.setId(id);
        return customer;
    }

    @Override
    public Customer getCustomer(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_BY_ID, this::mapRowToCustomer, id);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Customer> getAll() {
        return jdbcTemplate.query(SELECT_All_CUSTOMER, this::mapRowToCustomer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER,
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getCity(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getStreet(),
                customer.getZip());


    }

    @Override
    public void deleteCustomer(int id) {
        jdbcTemplate.update(DELETE_CUSTOMER, id);
    }

    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setStreet(rs.getString("street"));
        customer.setCity(rs.getString("city"));
        customer.setZip(rs.getString("zip"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));


        return customer;
    }
//    customer_id int(11) not null auto_increment primary key,
//    first_name varchar(50) not null,
//    last_name varchar(50) not null,
//    street varchar(50) not null,
//    city varchar(50) not null,
//    zip varchar(10) not null,
//    email varchar(75) not null,
//    phone varchar(20) not null
}


