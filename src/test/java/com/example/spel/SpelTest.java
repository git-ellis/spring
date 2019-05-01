package com.example.spel;

import com.example.domain.Department;
import com.example.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spel/application.xml"})
public class SpelTest {
    @Autowired
    private Department department;

    @Autowired
    private Product product;

    @Value("#{systemProperties['user.dir']}")
    private String sysDir;

    @Test
    public void showDepartment() {
        System.out.println(department);
    }

    @Test
    public void showProduct() {
        System.out.println(product);
    }
}
