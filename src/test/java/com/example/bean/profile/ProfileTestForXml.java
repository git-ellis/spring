package com.example.bean.profile;


import com.example.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean/profile/application.xml")
@ActiveProfiles("prod")
public class ProfileTestForXml {

    @Autowired
    private Product product;

    @Test
    public void showProduct() {
        System.out.println(product);
    }
}
