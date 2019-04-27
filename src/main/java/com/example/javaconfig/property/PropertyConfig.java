package com.example.javaconfig.property;

import com.example.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


/**
 * properties所加載的內容存放到Environment中
 */
@Configuration
@PropertySource("classpath:application.properties")
public class PropertyConfig {

    /**
     * 等同於xml配置<context:property-placeholder />
     *
     * @return
     */
    @Bean
    public static  PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean
    @Profile("dev")
    public Product product_dev(@Value("${product.dev.id}") long id,
                               @Value("${product.dev.name}") String name,
                               @Value("${product.dev.price}") int price,
                               @Value("${product.dev.quantity}") int quanty) {
        return new Product(id, name, price, quanty);
    }

    @Bean
    @Profile("prod")
    public Product product_prod(@Value("${product.prod.id}") long id,
                                @Value("${product.prod.name}") String name,
                                @Value("${product.prod.price}") int price,
                                @Value("${product.prod.quantity}") int quanty) {
        return new Product(id, name, price, quanty);
    }


}
