package com.example.eatgo.interfaces;

import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@CrossOrigin
@RestController
public class RestaurantController {

    // 의존성 주입
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        return restaurant;
    }

    @PostMapping("restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource) throws URISyntaxException {
        String name = resource.getName();
        String address = resource.getAddress();
        Restaurant restaurant = Restaurant.builder()
                .name(resource.getName())
                .address(resource.getAddress())
                .build();

        restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable Long id, @RequestBody Restaurant resource) {
        String name = resource.getName();
        String address = resource.getAddress();
        restaurantService.updateRestaurant(id, name, address);

        return "{}";
    }
}
