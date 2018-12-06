package com.example.takearest.service;

import com.example.takearest.entity.Meal;
import com.example.takearest.entity.Restaurant;

import java.util.List;
import java.util.Set;


/**
 * @author JavaSolutionsGuide
 *
 */
public interface RestaurantService {
    List<Restaurant> retrieveAll();

    //Set<Meal> getMeals(Long restaurantId);

    Restaurant getById(Long restaurantId);

    void save(Restaurant restaurant);

    void delete(Long restaurantId);

    //void updateRestaurant(Restaurant restaurant);

    void vote(Long restaurantId);

//    @Query("SELECT r FROM RESTAURANT r WHERE r.id=:restaurantId AND m.user.id=:userId")
//    Restaurant vote(@Param("id") int id, @Param("userId") int userId);
}