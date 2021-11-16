package com.example.yummers.models;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private List<Item> items;
    private double totalCost;
    private String orderID;
    private String customerID;
    public Order() {
    }

    public Order(List<Item> items) {
        totalCost = 0;
        this.items = items;
        for(Item item : items){
            totalCost += item.getPrice();
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setItems(List<Item> items) {
        totalCost = 0;
        this.items = items;
        for(Item item : items){
            totalCost += item.getPrice();
        }
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", totalCost=" + totalCost +
                ", orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                '}';
    }
}
