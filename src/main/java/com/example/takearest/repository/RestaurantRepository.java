package com.example.takearest.repository;

import com.example.takearest.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    Optional<Restaurant> findByName(String name);

}