package com.example.hello.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTest {
    private String name;
    private int age;
    @JsonProperty("phone_number")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserTest() {
        this.name= null;
        this.age = 0;
        this.phoneNumber = null;
    }

    public UserTest(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
