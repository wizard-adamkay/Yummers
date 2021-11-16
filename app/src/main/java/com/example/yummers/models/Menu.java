package com.example.yummers.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Menu implements Serializable {
    private List<Item> items;

    public Menu(List<Item> items) {
        this.items = items;
    }

    public Menu() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "items=" + items +
                '}';
    }
}
