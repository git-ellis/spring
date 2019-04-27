package com.example.domain;

public abstract class Phone {
    String brand;
    String model;
    String placeOfManufacture;

    public Phone() {
    }

    public Phone(String brand, String model, String placeOfManufacture) {
        this.brand = brand;
        this.model = model;
        this.placeOfManufacture = placeOfManufacture;
    }

    public abstract String showDetails();

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlaceOfManufacture() {
        return placeOfManufacture;
    }

    public void setPlaceOfManufacture(String placeOfManufacture) {
        this.placeOfManufacture = placeOfManufacture;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", placeOfManufacture='" + placeOfManufacture + '\'' +
                '}';
    }
}
