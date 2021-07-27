package com.example.eatgo.domain;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {
    List<MenuItem> menuItems = new ArrayList<>();

    public MenuItemRepositoryImpl() {
        menuItems.add(new MenuItem("Kimchi"));
    }

    @Override
    public List<MenuItem> findByRestaurantId(Long id) {
        return menuItems;
    }
}
