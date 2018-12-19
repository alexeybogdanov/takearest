package com.example.takearest.repository;

import com.example.takearest.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {

    Long findByName(String name);
}