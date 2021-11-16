package com.example.yummers.models;

import java.io.Serializable;

public class Order implements Serializable {
    private Item[] items;
    private double totalCost;

    public Order() {
    }

    public Order(Item[] items) {
        this.items = items;
        for(Item item : items){
            totalCost += item.getPrice();
        }
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        totalCost = 0;
        this.items = items;
        for(Item item : items){
            totalCost += item.getPrice();
        }
    }

    public double getTotalCost() {
        return totalCost;
    }
}
