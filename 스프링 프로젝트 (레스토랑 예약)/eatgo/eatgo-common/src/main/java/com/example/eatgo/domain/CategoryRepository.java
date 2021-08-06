package com.example.eatgo.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();

    Category save(Category category);
}
