package com.example.javaconfig.profile;

import com.example.domain.IPhoneX;
import com.example.domain.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Bean
    @Profile("prod")
    public Product product_prod() {
        Product product = new Product();
        product.setId(1L);
        product.setName("正式產品");
        product.setPrice(9999);
        product.setQuantity(10000);

        return product;
    }

    @Bean
    @Qualifier("product_dev")
    @Profile("dev")
    public Product product_dev() {
        Product product = new Product();
        product.setId(2L);
        product.setName("開發產品");
        product.setPrice(199);
        product.setQuantity(1000);

        return product;
    }

    /**
     * 沒有配置profile的bean物件都會被創建
     *
     * @return
     */
    @Bean
    public Product product_1() {
        Product product = new Product();
        product.setId(3L);
        product.setName("普通產品");
        product.setPrice(8788);
        product.setQuantity(100);

        return product;
    }

    @Bean
    @Conditional(TestCondition.class)
    public IPhoneX iphoneX() {
        return new IPhoneX();
    }

}
