package com.example.yummers;

public class Menus {
    String name;
    double price;
    String owner;

    public Menus() {}

    public Menus(String name, double price, String owner) {
        this.name = name;
        this.price = price;
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setOwner(String owner) { this.owner = owner; }

    public String getOwner() { return owner; }
}