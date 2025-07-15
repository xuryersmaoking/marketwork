package com.example.marketwork;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String description;
    private double price;
    private int imageResId;

    public Product(String name, String description, double price, int imageResId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}





