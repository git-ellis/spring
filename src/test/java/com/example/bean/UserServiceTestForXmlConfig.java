package com.example.bean;

import com.example.domain.User;
import com.example.service.bean.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-test.xml" })
public class UserServiceTestForXmlConfig {
    @Autowired
    private ApplicationContext context;

    @Qualifier("userService")
    @Autowired
    private UserService userService;

    @Test
    public void showUser1() {
        UserService us = (UserService) context.getBean("userService1");
        us.showUser();
    }

    @Test
    public void showUser3() {
        UserService us = (UserService) context.getBean("userService3");
        us.showUser();
    }

    @Test
    public void showUser4() {
        UserService us = (UserService) context.getBean("userService4");
        us.showUser();
    }

    @Test
    public void showUserList() {
        System.out.println("---------- UserList ----------");
        for (User user : userService.getUsers()) {
            System.out.println(user);
        }
    }

    @Test
    public void showUserSet() {
        System.out.println("---------- UserSet ----------");
        for (User user : userService.getUserSet()) {
            System.out.println(user);
        }
    }

    @Test
    public void showUserMap() {
        System.out.println("---------- UserMap ----------");
        for (Map.Entry<String, User> entry  : userService.getUserMap().entrySet()) {
            String key = entry.getKey();
            System.out.println(key + " = " + entry.getValue());
        }
    }
}