package com.example.eatgo.domain;
import java.util.*;

public class Restaurant {

    private Long id;
    private String name;
    private String address;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Restaurant() {

    }

    public String getName() {
        return name;
    }

    public String getInformation() {
        return name + " in " + address;
    }

    public String getAddress() {
        return address;
    }

    public long getId() {
        return id;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems) {
            addMenuItem(menuItem);
        }
     }
}
