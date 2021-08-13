package com.example.eatgo.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findById(Long id);
}
