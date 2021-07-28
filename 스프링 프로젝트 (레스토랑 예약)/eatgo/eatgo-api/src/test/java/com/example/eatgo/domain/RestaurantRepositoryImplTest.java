package com.example.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantRepositoryImplTest {

    @Test
    public void save() {
        //
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
        int oldCount = restaurantRepository.findAll().size();
        Restaurant restaurant = new Restaurant("BeRyong", "Seoul");
        restaurantRepository.save(restaurant);

        assertEquals(1234L, restaurant.getId());

        int newCount = restaurantRepository.findAll().size();
        assertEquals(1, newCount-oldCount);
    }

}