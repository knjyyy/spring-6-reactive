package com.spring6.spring6r2dbc.mapper;

import com.spring6.spring6r2dbc.domain.Customer;
import com.spring6.spring6r2dbc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToCustomerDTO(Customer customer);
}
