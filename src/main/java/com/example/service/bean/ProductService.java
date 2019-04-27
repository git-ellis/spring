package com.example.service.bean;

import com.example.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    @Autowired
    private Product product;


    public ProductService() {
    }

    @Autowired
    public ProductService(Product product) {
        this.product = product;
    }

    public Product getDepartment() {
        return product;
    }

    @Autowired
    public void setDepartment(Product department) {
        this.product = product;
    }

    public void showDetail() {
        System.out.println(product.toString());
    }
}
