package com.spring6.spring6r2dbc.controller;

import com.spring6.spring6r2dbc.model.BeerDTO;
import com.spring6.spring6r2dbc.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BeerController {
    public static final String BEER_PATH = "/api/v2/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{id}";

    private final BeerService beerService;

    @GetMapping(BEER_PATH_ID)
    Mono<BeerDTO> getBeerById(@PathVariable Integer id) {
        return  beerService.getById(id);
    }

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> listBeers() {
        return beerService.listBeers();
    }

    @PostMapping(BEER_PATH)
    Mono<ResponseEntity<Void>> createNewBeer(@Validated @RequestBody BeerDTO beerDTO) {
        return beerService.saveNewBeer(beerDTO).map(savedBeer ->
            ResponseEntity.created(UriComponentsBuilder.fromHttpUrl(
                        "http://localhost:8080/" + BEER_PATH + "/" + savedBeer.getId())
                        .build()
                        .toUri())
                    .build());
    }

    @PutMapping(BEER_PATH_ID)
    Mono<ResponseEntity<Void>> updateBeer(@PathVariable Integer id, @Validated @RequestBody BeerDTO beerDTO) {
        return beerService.updateBeer(id, beerDTO).map(savedDto -> ResponseEntity.ok().build());

    }

    @PatchMapping(BEER_PATH_ID)
    Mono<ResponseEntity<Void>> patchBeer(@PathVariable Integer id, @Validated @RequestBody BeerDTO beerDTO) {
        return beerService.patchBeer(id, beerDTO).map(patchedBeer -> ResponseEntity.ok().build());
    }

    @DeleteMapping(BEER_PATH_ID)
    Mono<ResponseEntity<Void>> deleteBeerById(@PathVariable Integer id) {
        return beerService.deleteBeer(id).map(response -> ResponseEntity.noContent().build());
    }
}
