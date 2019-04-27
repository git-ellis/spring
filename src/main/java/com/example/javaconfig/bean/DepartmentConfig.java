package com.example.javaconfig.bean;

import com.example.domain.Department;
import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DepartmentConfig {
    @Qualifier("employee1")
    @Autowired
    private Employee employee1;
    @Qualifier("employee2")
    @Autowired
    private Employee employee2;

    @Bean
    public Department department() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        Department department = new Department();
        department.setName("測試");
        department.setEmployees(employees);

        return department;
    }
}
