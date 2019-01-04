package com.example.takearest.service.impl;

import com.example.takearest.entity.Meal;
import com.example.takearest.entity.Restaurant;
import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;
import com.example.takearest.repository.RestaurantRepository;
import com.example.takearest.repository.UserRepository;
import com.example.takearest.repository.VoteRepository;
import com.example.takearest.service.api.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

//    @Override
//    public List<Meal> getMealsByRestaurant(long restaurantId) {
//        return restaurantRepository.get;
//    }

    @Override
    public Optional<Restaurant> getById(long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    @Override
    public Optional<Restaurant> getByName(String name) {
        return restaurantRepository.getByName(name);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public void delete(long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

}