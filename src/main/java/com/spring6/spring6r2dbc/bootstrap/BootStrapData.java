package com.spring6.spring6r2dbc.bootstrap;

import com.spring6.spring6r2dbc.domain.Beer;
import com.spring6.spring6r2dbc.domain.Customer;
import com.spring6.spring6r2dbc.repository.BeerRepository;
import com.spring6.spring6r2dbc.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class BootStrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data....");
        loadBeerData();
        beerRepository.count().subscribe(count -> {System.out.println("Beer Count: " + count);});

        loadCustomerData();
        customerRepository.count().subscribe(count -> { System.out.println("Customer count: " + count); });
    }

    private void loadBeerData() {
        beerRepository.count().subscribe(count -> {
            if(count == 0 ) {
                Beer beer1 = Beer.builder()
                        .beerStyle("Ale")
                        .beerName("Galaxy Cat")
                        .upc("123456")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122).createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer2 = Beer.builder()
                        .beerStyle("Pilsner")
                        .beerName("San Miguel Beer")
                        .upc("23452")
                        .price(new BigDecimal("5.99"))
                        .quantityOnHand(80).createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer3 = Beer.builder()
                        .beerStyle("Stout")
                        .beerName("Guiness Extra Stout")
                        .upc("456734")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(50).createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                //beerRepository.saveAll(Arrays.asList(beer1, beer2, beer3)).subscribe();
                beerRepository.save(beer1).subscribe();
                beerRepository.save(beer2).subscribe();
                beerRepository.save(beer3).subscribe();
            }
        });
    }

    private void loadCustomerData() {
        customerRepository.count().subscribe(count -> {
            if(count == 0) {
                Customer customer1 = Customer.builder()
                        .name("Michael Scott")
                        .build();
                Customer customer2 = Customer.builder()
                        .name("Dwight Schrute")
                        .build();
                Customer customer3 = Customer.builder()
                        .name("Kevin Malone")
                        .build();

                customerRepository.save(customer1).subscribe();
                customerRepository.save(customer2).subscribe();
                customerRepository.save(customer3).subscribe();
            }
        });
    }
}
