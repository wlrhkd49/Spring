package com.example.eatgo.application;

import com.example.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public Restaurant getRestaurant(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()->new RestaurantNotFoundException(id));
        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        List<Review> reviews = reviewRepository.findByRestaurantId(id);
        restaurant.setReviews(reviews);
        return restaurant;

    }

    public List<Restaurant> getRestaurants(String region, Long categoryId) {
        //TODO: categoryId 사용

        List<Restaurant> restaurants =
                restaurantRepository.findAllByAddressContainingAndCategoryId(region, categoryId);
        return restaurants;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional // 이 변경이 범위를 벗어날 때 다같이 적용
    public Restaurant updateRestaurant(long id, String name, String address) {
        //TODO: update Restaurant
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        restaurant.updateInformation(name, address);

        return restaurant;
    }
}
