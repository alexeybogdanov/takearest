package com.example.takearest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.takearest.entity.Food;
import com.example.takearest.entity.Restaurant;
import com.example.takearest.repository.RestaurantRepository;
import com.example.takearest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> retrieveRestaurants() {
        Iterable<Restaurant> restaurants = restaurantRepository.findAll();
        return (List<Restaurant>) restaurants;
    }

    public Set<Food> getRestaurant(Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);


        return optionalRestaurant.get().getFoodItems();
    }

    public void saveRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long restaurantId){
        restaurantRepository.deleteById(restaurantId);
    }

    public void updateRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void vote(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        Long currentVote =  restaurant.getVote();
        if (currentVote == null) {
            currentVote = 0L;
        }
        restaurant.setVote(++currentVote);
        restaurantRepository.save(restaurant);
    }
}