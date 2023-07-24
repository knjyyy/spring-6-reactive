package com.spring6.spring6r2dbc.mapper;

import com.spring6.spring6r2dbc.domain.Beer;
import com.spring6.spring6r2dbc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO beerDto);
    BeerDTO beerToBeerDto(Beer beer);
}
