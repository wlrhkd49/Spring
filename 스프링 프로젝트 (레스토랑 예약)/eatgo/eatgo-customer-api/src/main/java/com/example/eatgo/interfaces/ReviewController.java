package com.example.eatgo.interfaces;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.Review;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable Long restaurantId,
            @Valid @RequestBody Review resource
    ) throws URISyntaxException {
        /**
         * Authentication은 Authorization: Bearer <token> 형태!
         */
        Claims claims = (Claims) authentication.getPrincipal();

        String name = claims.get("name", String.class);
        Review review = reviewService.addReview(
                restaurantId,
                name,
                resource.getScore(),
                resource.getDescription());


        String url = "/restaurants/"+restaurantId+"/reviews/" + review.getId();
        return ResponseEntity.created(new URI(url))
            .body("{}");
    }
}
