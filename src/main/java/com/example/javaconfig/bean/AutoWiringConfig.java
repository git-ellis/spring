package com.example.javaconfig.bean;

import com.example.domain.Phone;
import com.example.javaconfig.bean.PhoneConfig;
import com.example.service.bean.UserService;
import com.example.domain.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @ComponentScan 預設掃描同層目錄和下層目錄的@component
 * @ComponentScan(basePackages = {"com.example.domain"}) 透過basePackages參數指定基礎包
 * @ComponentScan(basePackageClasses = {User.class}) 透過basePackageClasses指定基礎的類別，會把該列別所在的包當成基礎包
 */
@Configuration
@ComponentScan(basePackageClasses = {User.class, UserService.class, Phone.class})
@Import(PhoneConfig.class)
public class AutoWiringConfig {
}
