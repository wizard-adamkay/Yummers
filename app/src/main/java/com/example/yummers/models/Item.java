package com.example.yummers.models;

import java.io.Serializable;
import java.util.Arrays;

public class Item implements Serializable {
    private String name;
    private double price;
    private String[] tags;

    public Item() {
    }

    public Item(String name, double price, String[] tags) {
        this.name = name;
        this.price = price;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}
