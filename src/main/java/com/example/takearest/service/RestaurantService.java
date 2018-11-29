package com.example.takearest.service;

import com.example.takearest.entity.Food;
import com.example.takearest.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;


/**
 * @author JavaSolutionsGuide
 *
 */
public interface RestaurantService {
    List<Restaurant> retrieveRestaurants();

    Set<Food> getRestaurant(Long restaurantId);

    void saveRestaurant(Restaurant restaurant);

    void deleteRestaurant(Long restaurantId);

    void updateRestaurant(Restaurant restaurant);

    void vote(Long restaurantId);

//    @Query("SELECT r FROM RESTAURANT r WHERE r.id=:restaurantId AND m.user.id=:userId")
//    Restaurant vote(@Param("id") int id, @Param("userId") int userId);
}