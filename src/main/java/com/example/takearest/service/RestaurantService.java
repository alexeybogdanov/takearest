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

    Long getByName(String name);

    void save(Restaurant restaurant);

    void delete(Long restaurantId);

    void vote(Long restaurantId);

    //void updateRestaurant(Restaurant restaurant);



//    @Query("SELECT r FROM RESTAURANT r WHERE r.id=:restaurantId AND m.user.id=:userId")
//    Restaurant vote(@Param("id") int id, @Param("userId") int userId);
}