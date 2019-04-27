package com.example.domain;

import org.springframework.stereotype.Component;

@Component
public class Pixel extends Phone {

    public Pixel() {
    }

    public Pixel(String brand, String model, String placeOfManufacture) {
        super(brand, model, placeOfManufacture);
    }

    @Override
    public String showDetails() {
        return toString();
    }
}
