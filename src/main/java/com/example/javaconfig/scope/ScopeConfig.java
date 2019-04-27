package com.example.javaconfig.scope;

import com.example.domain.IPhone;
import com.example.domain.Phone;
import com.example.domain.Product;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class ScopeConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Product product1() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Milk");
        product.setPrice(100);
        product.setQuantity(1000);

        return product;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Product product2() {
        Product product = new Product();
        product.setId(2L);
        product.setName("eggnog");
        product.setPrice(350);
        product.setQuantity(200);

        return product;
    }

    /**
     * (spring在啟動的時候就會掃瞄並嘗試自動裝配，但是在裝配的當下，reqeust和session物件並不存在)
     * 所以如果是request, session scope
     * spring不會將該bean實際注入(Autowiring)
     * 而是生成一個該bean的代理，將其注入，
     * 當調用到該bean的方法的時候代理物件才會委派給scope內真正的bean物件動作
     * (所以表示該代理必須擁有和注入的bean物件相同的方法)
     * ScopedProxyMode.TARGET_CLASS 該注入bean是一個class
     * ScopedProxyMode.INTERFACES   該注入bean是一個interface
     *
     */

    /**
     * 將scope設定為session scope
     * proxy mode = 如果待注入的bean是class，則使用CGlib生成class代理
     * @return product
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Product product3() {
        Product product = new Product();
        product.setId(2L);
        product.setName("eggnog");
        product.setPrice(350);
        product.setQuantity(200);

        return product;
    }

    /**
     * 將scope設定為session scope
     * proxy mode = 如果待注入的bean是interface，則配置ScopedProxyMode.INTERFACES表明代理會implements這個介面
     * @return
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
    public Phone iphone7() {
        return new IPhone("Apple", "7", "China");
    }


}
