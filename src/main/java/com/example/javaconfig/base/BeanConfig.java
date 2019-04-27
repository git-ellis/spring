package com.example.javaconfig.base;

import com.example.javaconfig.bean.DepartmentConfig;
import com.example.javaconfig.bean.EmployeeConfig;
import com.example.javaconfig.bean.UserConfig;
import com.example.javaconfig.bean.UserServiceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * root configuration
 *
 * @Configuration 表示該類別為配置類別，負責定義創建Bean的細節
 * @Import 引入其他java config
 * @ImportResource 從XML配置引入
 */
@Configuration
@Import({DepartmentConfig.class, EmployeeConfig.class, UserConfig.class, UserServiceConfig.class})
@ImportResource("classpath:application.xml")
public class BeanConfig {

}
