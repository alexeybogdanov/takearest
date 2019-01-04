package com.example.takearest.service.api;

import com.example.takearest.entity.Meal;
import com.example.takearest.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RestaurantService {
    List<Restaurant> retrieveAll();

//    List<Meal> getMealsByRestaurant(long restaurantId);

    Optional<Restaurant> getById(long restaurantId);

    Optional<Restaurant> getByName(String name);

    Restaurant save(Restaurant restaurant);

    void delete(long restaurantId);

}