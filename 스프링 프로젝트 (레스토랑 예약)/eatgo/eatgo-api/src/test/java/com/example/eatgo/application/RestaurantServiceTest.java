package com.example.eatgo.application;

import com.example.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.verify;

class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Mock어노테이션 붙은거에 객체 처리
        
        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();
        
        restaurantService = new RestaurantService(
                restaurantRepository, menuItemRepository, reviewRepository
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

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
        .name("BeRyong")
        .score(1)
        .description("Bad")
        .build());

        given(reviewRepository.findByRestaurantId(1004L))
                .willReturn(reviews);
    }

    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        verify(menuItemRepository).findByRestaurantId(eq(1004L));
        verify(reviewRepository).findByRestaurantId(eq(1004L));
        assertEquals(1004L, restaurant.getId());

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertEquals("Kimchi", menuItem.getName());

        Review review = restaurant.getReviews().get(0);
        assertEquals("Bad", review.getDescription());
    }

//    @Test()
//    public void getRestaurantWithNotExisted() {
//        restaurantService.getRestaurant(404L);
//        assertThrows(RestaurantNotFoundException.class, () -> {
//        });
//    }

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