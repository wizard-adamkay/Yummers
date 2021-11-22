package com.example.yummers.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu implements Serializable {
    private ArrayList<Item> items;
    private String owner;
    public Menu(ArrayList<Item> items, String owner) {
        this.items = items;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item i) {
        this.items.add(i);
    }
    @Override
    public String toString() {
        return "Menu{" +
                "items=" + items.toString() +
                '}';
    }
}
