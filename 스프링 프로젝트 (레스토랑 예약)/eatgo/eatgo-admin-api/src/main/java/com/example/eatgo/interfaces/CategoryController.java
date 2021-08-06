package com.example.eatgo.interfaces;

import com.example.eatgo.application.CategoryService;
import com.example.eatgo.application.RegionService;
import com.example.eatgo.domain.Category;
import com.example.eatgo.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categorys")
    public List<Category> list() {
        List<Category> categorys = categoryService.getCategorys();
        return categorys;
    }

    @PostMapping("/categorys")
    public ResponseEntity<?> create(@RequestBody Category resource) throws URISyntaxException {
        String name = resource.getName();
        Category category = categoryService.addCategory(name);

        String url = "/categorys/" + category.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
