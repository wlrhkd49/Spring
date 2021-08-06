package com.example.eatgo.application;

import com.example.eatgo.domain.Category;
import com.example.eatgo.domain.CategoryRepository;
import com.example.eatgo.domain.Region;
import com.example.eatgo.domain.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategorys() {
        List<Category> mockCategorys = new ArrayList<>();
        mockCategorys.add(Category.builder().name("Korean Food").build());

        given(categoryRepository.findAll()).willReturn(mockCategorys);

        List<Category> categorys = categoryService.getCategorys();
        Category category = categorys.get(0);

        assertEquals("Korean Food", category.getName());
    }

    @Test
    public void addCategory() {
        Category category = categoryService.addCategory("Korean Food");

        verify(categoryRepository).save(any());

        assertEquals("Korean Food", category.getName());
    }


}