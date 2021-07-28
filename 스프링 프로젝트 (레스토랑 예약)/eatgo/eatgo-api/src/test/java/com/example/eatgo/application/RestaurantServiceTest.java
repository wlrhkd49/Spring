package com.example.eatgo.application;

import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.MenuItemRepository;
import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Mock어노테이션 붙은거에 객체 처리
        mockRestaurantRepository();
        mockMenuItemRepository();
        restaurantService = new RestaurantService(
                restaurantRepository, menuItemRepository
        );
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems  = new ArrayList<>();
        menuItems.add(MenuItem.builder()
            .name("Kimchi")
            .build());
        given(menuItemRepository.findByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        //Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));


    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assertEquals(1004L, restaurant.getId());

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertEquals("Kimchi", menuItem.getName());
    }
    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        assertEquals(1004L, restaurants.get(0).getId());
    }

    @Test
    public void addRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();



        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertEquals(1234L, created.getId());
    }

    @Test
    public void updateRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
        restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");

        assertEquals("Sool zip", restaurant.getName());
        assertEquals("Busan", restaurant.getAddress());
    }

}