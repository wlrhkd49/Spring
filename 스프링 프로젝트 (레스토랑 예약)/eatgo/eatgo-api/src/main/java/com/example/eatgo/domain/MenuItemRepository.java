package com.example.eatgo.domain;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findByRestaurantId(Long id);
}
