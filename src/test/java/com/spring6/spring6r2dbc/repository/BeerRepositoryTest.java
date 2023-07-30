package com.spring6.spring6r2dbc.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring6.spring6r2dbc.domain.Beer;
import config.DatabaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

@DataR2dbcTest
@Import(DatabaseConfig.class)
public class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveNewBeer() {
        beerRepository.save(getTestBeer()).subscribe(beer -> System.out.println(beer.toString()));
    }

    @Test
    void testCreateJson () throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(getTestBeer()));
    }

    public static Beer getTestBeer() {
        return Beer.builder()
                .beerName("San Miguel Pale Pilsen")
                .beerStyle("Pilsen")
                .upc("123123123")
                .quantityOnHand(200)
                .price(BigDecimal.TEN)
                .build();
    }
}