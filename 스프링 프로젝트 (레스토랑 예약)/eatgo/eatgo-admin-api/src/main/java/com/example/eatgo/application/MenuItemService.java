package com.example.eatgo.application;

import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.MenuItemRepository;
import com.example.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItems(Long restaurantId) {
            return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public void bulkUpdate(Long id, List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            update(id, menuItem);
        }
        // TODO: bulk update 수행

    }

    private void update(Long id, MenuItem menuItem) {
        if(menuItem.isDestroy()) {
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(id);
        menuItemRepository.save(menuItem);
    }
}
