package com.example.property;

import com.example.javaconfig.property.PropertyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)


@ContextConfiguration(classes = {PropertyConfig.class})
@ActiveProfiles("dev")
public class EnvironmentTest {
    @Autowired
    private Environment env;

    @Test
    public void show() {
        System.out.println("product.prod.id = " + env.getRequiredProperty("product.prod.id", Long.class));
        System.out.println("product.prod.name = " + env.getProperty("product.prod.name"));
        System.out.println("product.prod.price = " + env.getProperty("product.prod.price", Integer.class));
        System.out.println("product.prod.quantity = " + env.getProperty("product.prod.quantity", Integer.class));

        System.out.println("product.dev.id = " + env.getRequiredProperty("product.dev.id", Long.class));
        System.out.println("product.dev.name = " + env.getProperty("product.dev.name"));
        System.out.println("product.dev.price = " + env.getProperty("product.dev.price", Integer.class));
        System.out.println("product.dev.quantity = " + env.getProperty("product.dev.quantity", Integer.class));

        try {
            System.out.println("msg = " + env.getRequiredProperty("msg"));
        } catch (IllegalStateException e) {
            System.out.println(e);
        }

        System.out.println("contains property spring.profiles.active = " + env.containsProperty("spring.profiles.active"));
        System.out.println("contains property spring.profiles.default = " + env.containsProperty("spring.profiles.default"));

        System.out.println("--------------------");
        Arrays.stream(env.getActiveProfiles()).forEach(s -> System.out.println(s));
        Arrays.stream(env.getDefaultProfiles()).forEach(s -> System.out.println(s));

        System.out.println(env.resolvePlaceholders("${product.prod.id}"));
    }
}
