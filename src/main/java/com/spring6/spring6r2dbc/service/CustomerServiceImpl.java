package com.spring6.spring6r2dbc.service;

import com.spring6.spring6r2dbc.mapper.CustomerMapper;
import com.spring6.spring6r2dbc.model.CustomerDTO;
import com.spring6.spring6r2dbc.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Flux<CustomerDTO> getAll() {
        return customerRepository.findAll().map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> getById(Integer id) {
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> save(CustomerDTO customerDTO) {
        return customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO)).map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> updateCustomer(Integer id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(customerDTO.getName());
            return customer;
        }).flatMap(customerRepository::save).map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> patchCustomer(Integer id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if(StringUtils.hasText(customerDTO.getName())) {
                customer.setName(customerDTO.getName());
            }
            return customer;
        }).flatMap(customerRepository::save).map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<Void> deleteCustomer(Integer id) {
        return customerRepository.deleteById(id);
    }
}
