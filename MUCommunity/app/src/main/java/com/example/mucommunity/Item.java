package com.example.mucommunity;

public class Item {
    private String name;
    private double price;
    private String description;

    public Item() {}

    // Constructor
    public Item(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}