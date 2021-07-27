package com.example.eatgo.domain;

public class Restaurant {

    private Long id;
    private String name;
    private String address;

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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
}
