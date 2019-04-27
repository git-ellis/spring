package com.example.javaconfig.bean;

import com.example.service.bean.UserService;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

    @Bean
    public UserService uc1(@Autowired @Qualifier("u1") User user) {
        return new UserService(user);
    }
}
