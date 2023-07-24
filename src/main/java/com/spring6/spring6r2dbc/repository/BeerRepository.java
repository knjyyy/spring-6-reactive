package com.spring6.spring6r2dbc.repository;


import com.spring6.spring6r2dbc.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
