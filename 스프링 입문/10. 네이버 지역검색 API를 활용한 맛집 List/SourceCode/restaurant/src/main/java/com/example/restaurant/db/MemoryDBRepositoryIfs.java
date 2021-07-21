package com.example.restaurant.db;

import java.util.*;

public interface MemoryDBRepositoryIfs<T> {

    Optional<T> findById(int index);
    T save(T entity);
    void deleteById(int index);
    List<T> findAll();
}
