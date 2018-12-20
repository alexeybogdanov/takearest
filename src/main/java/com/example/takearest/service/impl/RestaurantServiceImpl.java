package com.example.takearest.service.impl;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;
import com.example.takearest.repository.RestaurantRepository;
import com.example.takearest.repository.UserRepository;
import com.example.takearest.repository.VoteRepository;
import com.example.takearest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    public void setRestaurantRepository(RestaurantRepository restaurantRepository, VoteRepository voteRepository,
                                        UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
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

    public void vote(Long restaurantId, String username) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        LocalDateTime time = LocalDateTime.now();
        User user = userRepository.getByUsername(username);
        Vote vote = new Vote();
        vote.setDate(time);
        vote.setRestaurant(restaurant);
        vote.setUser(user);
        voteRepository.save(vote);


    }
}