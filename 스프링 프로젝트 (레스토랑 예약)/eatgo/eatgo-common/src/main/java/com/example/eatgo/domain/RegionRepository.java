package com.example.eatgo.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.*;
public interface RegionRepository extends CrudRepository<Region, Long> {
    List<Region> findAll();
}
