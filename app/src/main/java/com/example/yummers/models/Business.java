package com.example.yummers.models;

import java.io.Serializable;
import java.util.List;

public class Business implements Serializable {
    private String address;
    private String name;
    private String phone;
    private String owner;
    private String ID;
    private Menu menu;
    private List<Order> currentOrders;
    private List<Order> orderHistory;

    public Business() {
    }

    public Business(String name, String address, String phone, String owner) {
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.owner = owner;
        this.menu = null;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Order> getCurrentOrders() {
        return currentOrders;
    }

    public void setCurrentOrders(List<Order> currentOrders) {
        this.currentOrders = currentOrders;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Business{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", owner='" + owner + '\'' +
                ", meun=" + menu + '/' +
                '}';
    }
}
