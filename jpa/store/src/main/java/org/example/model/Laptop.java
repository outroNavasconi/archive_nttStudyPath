package org.example.model;

import jakarta.persistence.Entity;

@Entity
public class Laptop extends Product {
    private String model;
    private String brand;

    public Laptop(){}

    public Laptop(String model, String brand) {
        this.model = model;
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
