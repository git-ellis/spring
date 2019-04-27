package com.example.service.bean;

import com.example.domain.Department;
import com.example.domain.Employee;

import java.util.List;

public class DepartmentService {

    private Department department;

    public DepartmentService() {
    }

    public DepartmentService(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void showDetail() {
        String name = department.getName();
        List<Employee> employees = department.getEmployees();

        System.out.println("department name = " + name);
        System.out.println("the list below is the employees of this department: ");

        for (Employee e : employees)
            System.out.println(e);
    }
}
