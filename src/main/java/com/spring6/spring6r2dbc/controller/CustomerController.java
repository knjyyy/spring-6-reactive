package com.spring6.spring6r2dbc.controller;

import com.spring6.spring6r2dbc.model.CustomerDTO;
import com.spring6.spring6r2dbc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v2/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{id}";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    Flux<CustomerDTO> getAll() {
        return customerService.getAll();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    Mono<CustomerDTO> getById(@PathVariable Integer id) {
        return customerService.getById(id).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping(CUSTOMER_PATH)
    Mono<ResponseEntity<Void>> createCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.save(customerDTO).map(savedCustomer -> ResponseEntity.created(
                    UriComponentsBuilder.fromHttpUrl(
                            "http://localhost:8080/" + CUSTOMER_PATH + "/" + savedCustomer.getId())
                            .build().toUri())
                .build());
    }

    @PutMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<Void>> updateCustomer(@PathVariable Integer id, @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService
                .updateCustomer(id, customerDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(updatedCustomer -> ResponseEntity.noContent().build());
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<Void>> patchCustomer(@PathVariable Integer id, @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(id, customerDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(patchCustomer -> ResponseEntity.noContent().build());
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable Integer id) {
        return customerService
                .getById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(customerDTO -> customerService.deleteCustomer(customerDTO.getId()))
                .thenReturn(ResponseEntity.noContent().build());
    }
}
