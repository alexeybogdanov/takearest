package com.example.takearest.service.impl;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.repository.RestaurantRepository;
import com.example.takearest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> retrieveAll() {
        Iterable<Restaurant> restaurants = restaurantRepository.findAll();
        return (List<Restaurant>) restaurants;
    }

//    public Set<Meal> getMeals(Long restaurantId) {
//        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
//
//        return optionalRestaurant.get().getMeals();
//    }

    @Override
    public Restaurant getById(Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        return optionalRestaurant.get();
    }

    @Override
    public Long getByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public void save(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public void delete(Long restaurantId){
        restaurantRepository.deleteById(restaurantId);
    }

    public void updateRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void vote(Long restaurantId) {
//        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
//        Integer currentVote =  restaurant.getVote().getValue();
//        if (currentVote.equals(null)) {
//            currentVote = 0;
//        }
//        Vote vote = new Vote();
//        vote.setValue(currentVote);
//        restaurant.setVote(vote);
//        restaurantRepository.save(restaurant);
    }
}