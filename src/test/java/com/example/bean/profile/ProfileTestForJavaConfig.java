package com.example.bean.profile;


import com.example.domain.Phone;
import com.example.domain.Product;
import com.example.javaconfig.profile.ProfileConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProfileConfig.class})
@ActiveProfiles("dev")
public class ProfileTestForJavaConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Environment environment;

    @Autowired
    @Qualifier("product_dev")
    private Product product;

    @Autowired
    @Qualifier("product_1")
    private Product normalProduct;

    @Autowired(required = false)
    private Phone iphonex;

    @Test
    public void showProduct() {
        System.out.println(product.toString());
    }

    @Test
    public void showNormalProduct() {
        System.out.println("NormalProduct = " + normalProduct.toString());
    }

    @Test
    public void getActiveProfiles() {
//        environment.
//        for (final String profileName : environment.getActiveProfiles()) {
//            System.out.println("Currently active profile - " + profileName);
//        }
    }

    @Test
    public void printBeans() {
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    }

    @Test
    public void showIphonex() {
        System.out.println(iphonex);
    }

}
