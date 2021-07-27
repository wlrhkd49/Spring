package com.example.eatgo.interfaces;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class RestaurantController {

    // 의존성 주입
    @Autowired
    private RestaurantRepositoryImpl repository;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = repository.findAll();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id) {
        Restaurant restaurant = repository.findById(id);

        return restaurant;
    }

}
