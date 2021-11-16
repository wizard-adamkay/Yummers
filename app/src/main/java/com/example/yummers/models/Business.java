package com.example.yummers.models;

import java.io.Serializable;

public class Business implements Serializable {
    private String address;
    private String name;
    private String phone;
    private String owner;
    private Menu menu;
    private Order[] currentOrders;
    private Order[] orderHistory;
    public Business(String name, String address, String phone, String owner) {
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.owner = owner;
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

    @Override
    public String toString() {
        return "Business{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
