package com.example.eatgo.interfaces;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
@RestController
public class RestaurantController {

    @GetMapping("/restaurants")
    public List<Restaurant> list() {

        RestaurantRepository repository = new RestaurantRepository();
        List<Restaurant> restaurants = repository.findAll();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id) {
        RestaurantRepository repository = new RestaurantRepository();
        Restaurant restaurant = repository.findById(id);

        return restaurant;
    }

}
