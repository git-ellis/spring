package com.example.bean;

import com.example.javaconfig.base.BeanConfig;
import com.example.service.bean.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfig.class)
public class UserServiceTestForJavaConfig {
    @Autowired
//    @Resource(name = "uc1")
    @Qualifier("uc1")
    private UserService us;

    @Test
    public void showUser() {
        us.showUser();
    }
}