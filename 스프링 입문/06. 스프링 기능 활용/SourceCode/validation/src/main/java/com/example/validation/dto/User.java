package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

public class User {

    @NotBlank // 띄워쓰기, 공백, null 안됨
    private String name;

    @Max(value = 90)
    private int age;

    @Valid // 오브젝트 형태라면 반드시 controller뿐만 아니라 여기도 Valid 필수!
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
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

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }
}
