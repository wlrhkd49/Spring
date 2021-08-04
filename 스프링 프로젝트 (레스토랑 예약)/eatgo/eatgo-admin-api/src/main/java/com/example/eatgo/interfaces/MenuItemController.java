package com.example.eatgo.interfaces;

import com.example.eatgo.application.MenuItemService;
import com.example.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(
            @PathVariable Long restaurantId,
            @RequestBody List<MenuItem> menuItems
    ) {
        menuItemService.bulkUpdate(restaurantId, menuItems);

        return "";
    }

    @GetMapping("/restaurants/{restaurantId}/menuitems")
    public List<MenuItem> findAll(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItems(restaurantId);
    }

}
