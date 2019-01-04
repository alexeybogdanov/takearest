package com.example.takearest.repository;

import com.example.takearest.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {

   Optional<Restaurant> getByName(String name);

}