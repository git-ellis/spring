package com.example.domain;

public class Note extends Phone {
    public Note(String brand, String model, String placeOfManufacture) {
        super(brand, model, placeOfManufacture);
    }

    @Override
    public String showDetails() {
        return toString();
    }
}
