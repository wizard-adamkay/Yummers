package com.example.yummers.models;

import java.io.Serializable;
import java.util.Arrays;

public class Menu implements Serializable {
    private Item[] items;

    public Menu(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
