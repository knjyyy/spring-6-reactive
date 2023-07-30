package com.spring6.spring6r2dbc.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring6.spring6r2dbc.domain.Customer;
import config.DatabaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

@DataR2dbcTest
@Import(DatabaseConfig.class)
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void saveNewCustomer() {
        customerRepository.save(getCustomer()).subscribe(customer -> System.out.println(customer.toString()));
    }

    @Test
    void testCreateJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(getCustomer()));
    }

    public static Customer getCustomer() {
        return Customer.builder().name("Ron Swanson").build();
    }
}
