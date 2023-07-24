package com.spring6.spring6r2dbc.service;

import com.spring6.spring6r2dbc.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {
    Flux<BeerDTO> listBeers();
    Mono<BeerDTO> getById(Integer id);

    Mono<BeerDTO> saveNewBeer(BeerDTO beerDto);

    Mono<BeerDTO> updateBeer(Integer id, BeerDTO beerDTO);

    Mono<BeerDTO> patchBeer(Integer id, BeerDTO beerDTO);

    Mono<Void> deleteBeer(Integer id);
}
