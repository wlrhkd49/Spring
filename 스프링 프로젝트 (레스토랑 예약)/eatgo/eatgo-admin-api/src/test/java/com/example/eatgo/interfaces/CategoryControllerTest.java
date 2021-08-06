package com.example.eatgo.interfaces;

import com.example.eatgo.application.CategoryService;
import com.example.eatgo.application.RegionService;
import com.example.eatgo.domain.Category;
import com.example.eatgo.domain.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // 스프링으로 테스트
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void list() throws Exception {
        List<Category> categorys = new ArrayList<>();
        categorys.add(Category.builder().name("Korean Food").build());

        given(categoryService.getCategorys()).willReturn(categorys);

        mvc.perform(get("/categorys"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Korean Food")));
    }

    @Test
    public void create() throws Exception {
        Category category = Category.builder().name("Korean Food").build();
        given(categoryService.addCategory("Korean Food")).willReturn(category);

        mvc.perform(post("/categorys")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Korean Food\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"));

        verify(categoryService).addCategory("Korean Food");
    }

}