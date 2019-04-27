package com.example.domain;

public class IPhone extends Phone {

    public IPhone(String brand, String model, String placeOfManufacture) {
        super(brand, model, placeOfManufacture);
    }

    @Override
    public String showDetails() {
        return toString();
    }
}
