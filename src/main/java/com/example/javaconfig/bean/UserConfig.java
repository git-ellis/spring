package com.example.javaconfig.bean;

import com.example.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public User u1() {
        User user = new User();
        user.setId(1);
        user.setName("Ray");
        user.setAge(30);
        user.setEmail("ray@gmail.com");
        user.setGender("male");

        return user;
    }
}
