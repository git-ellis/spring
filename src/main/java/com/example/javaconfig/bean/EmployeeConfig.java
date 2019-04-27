package com.example.javaconfig.bean;

import com.example.domain.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

    @Bean
    /**
     * @Bean 告訴spring該方法會回傳一個實例，並且要註冊到spring context
     */
    public Employee employee1() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Ben");
        employee.setAge(30);
        employee.setTitle("Engineer");
        return employee;
    }

    @Bean
    public Employee employee2() {
        Employee employee = new Employee();
        employee.setId(2);
        employee.setName("Una");
        employee.setAge(35);
        employee.setTitle("Designer");
        return employee;
    }
}
