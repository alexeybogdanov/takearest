package com.example.takearest.service;

import com.example.takearest.entity.Food;
import com.example.takearest.entity.Restaurant;

import java.util.List;
import java.util.Set;


/**
 * @author JavaSolutionsGuide
 *
 */
public interface RestaurantService {
    public List<Restaurant> retrieveRestaurants();

    public Set<Food> getRestaurant(Long restaurantId);

//    public Restaurant getRestaurantFull(Long restaurantId);

    public void saveRestaurant(Restaurant restaurant);

    public void deleteRestaurant(Long restaurantId);

    public void updateRestaurant(Restaurant restaurant);
}