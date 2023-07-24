package com.spring6.spring6r2dbc.service;

import com.spring6.spring6r2dbc.mapper.BeerMapper;
import com.spring6.spring6r2dbc.model.BeerDTO;
import com.spring6.spring6r2dbc.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll().map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> getById(Integer id) {
        return beerRepository.findById(id).map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> saveNewBeer(BeerDTO beerDto) {
        return beerRepository.save(beerMapper.beerDtoToBeer(beerDto)).map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> updateBeer(Integer id, BeerDTO beerDTO) {
        return beerRepository.findById(id).map(beer -> {
            beer.setBeerName(beerDTO.getBeerName());
            beer.setBeerStyle(beerDTO.getBeerStyle());
            beer.setPrice(beerDTO.getPrice());
            beer.setUpc(beerDTO.getUpc());
            beer.setQuantityOnHand(beerDTO.getQuantityOnHand());

            return beer;
        }).flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> patchBeer(Integer id, BeerDTO beerDTO) {
        return beerRepository.findById(id).map(beer -> {
            if(StringUtils.hasText(beerDTO.getBeerName())) {
                beer.setBeerName(beerDTO.getBeerName());
            }
            if(StringUtils.hasText(beerDTO.getBeerStyle())) {
                beer.setBeerStyle(beerDTO.getBeerStyle());
            }

            if(StringUtils.hasText(beerDTO.getUpc())) {
                beer.setUpc(beerDTO.getUpc());
            }

            if(beerDTO.getPrice() != null) {
                beer.setPrice(beerDTO.getPrice());
            }

            if(beerDTO.getQuantityOnHand() != null) {
                beer.setQuantityOnHand(beerDTO.getQuantityOnHand());
            }

            return beer;
        }).flatMap(beerRepository::save).map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<Void> deleteBeer(Integer id) {
        return beerRepository.deleteById(id);
    }
}
