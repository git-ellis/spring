package com.example.javaconfig.spel;

import com.example.domain.Department;
import com.example.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpelConfig {

    @Bean
    public Product product(@Value("#{1L}") long id,
                           @Value("'Test'") String name,
                           @Value("#{(new java.util.Random().nextInt() + 1)}") int price,
                           @Value("#{(new java.util.Random().nextInt() + 1)}") int quantity) {
        return new Product(id, name, price, quantity);
    }



}
