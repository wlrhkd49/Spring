package com.example.eatgo.domain;
import java.util.*;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByRestaurantId(Long restaurantId);

    Review save(Review review);
}
