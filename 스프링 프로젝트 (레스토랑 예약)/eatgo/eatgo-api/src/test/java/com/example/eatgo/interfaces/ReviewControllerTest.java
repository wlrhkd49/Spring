package com.example.eatgo.interfaces;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // 스프링으로 테스트
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;


    @Test
    public void createWithValidAttributes() throws Exception {
        given(reviewService.addReview(eq(1L),any())).willReturn(
                Review.builder()
                        .id(1004L)
                        .build()
        );

        mvc.perform(post("/restaurants/1/reviews")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"JOKER\",\"score\":3,\"description\":\"Mat-it-da\"}"))
                .andExpect(status().isCreated())
        .andExpect(header().string("location", "/restaurants/1/reviews/1004"));


        verify(reviewService).addReview(eq(1L),any());
    }

    @Test
    public void createWithInvalidAttributes() throws Exception {
        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(eq(1L),any());
    }


}