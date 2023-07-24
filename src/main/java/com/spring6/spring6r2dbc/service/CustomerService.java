package com.spring6.spring6r2dbc.service;

import com.spring6.spring6r2dbc.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<CustomerDTO> getAll();
    Mono<CustomerDTO> getById(Integer id);
    Mono<CustomerDTO> save(CustomerDTO customerDTO);

    Mono<CustomerDTO> updateCustomer(Integer id, CustomerDTO customerDTO);

    Mono<CustomerDTO> patchCustomer(Integer id, CustomerDTO customerDTO);

    Mono<Void> deleteCustomer(Integer id);
}
