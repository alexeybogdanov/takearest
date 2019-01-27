package com.example.takearest.repository;

import com.example.takearest.entity.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    Optional<Restaurant> findByName(String name);

    @Override
    @Query(value = "SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.meals dis")
//    @EntityGraph(attributePaths = "meals",  type = EntityGraph.EntityGraphType.LOAD)
    Iterable<Restaurant> findAll();
}