package com.example.eatgo.interfaces;

import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.*;

@ExtendWith(SpringExtension.class) // 스프링으로 테스트
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    // 가짜 객체 ( 실제와는 상관이 없음 )
    @MockBean
    private RestaurantService restaurantService;


    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build());
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
        .andExpect(content().string(containsString("\"name\":\"JOKER House\"")));
    }

    @Test
    public void detail() throws Exception {
        Restaurant restaurant1 = Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build();
        MenuItem menuItem = MenuItem.builder()
                .name("Kimchi")
                .build();
        restaurant1.setMenuItems(Arrays.asList(menuItem));

        Restaurant restaurant2 = Restaurant.builder()
                .id(2021L)
                .name("Cyber Food")
                .address("Seoul")
                .build();

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2021L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"JOKER House\"")))
                .andExpect(content().string(
                containsString("Kimchi")
        ));

        mvc.perform(get("/restaurants/2021"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2021")))
                .andExpect(content().string(containsString("\"name\":\"Cyber Food\"")));
    }

    @Test
    public void create() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            return Restaurant.builder()
                    .id(1234L)
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .build();
        });

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\", \"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/null"))
                .andExpect(content().string(""));

        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void update() throws Exception {

        mvc.perform(patch("/restaurants/1004")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"JOKER Bar\",\"address\":\"Busan\"}")
        )
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L, "JOKER Bar", "Busan");
    }

}