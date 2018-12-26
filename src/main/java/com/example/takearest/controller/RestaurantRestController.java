package com.example.takearest.controller;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.service.api.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/api/restaurants")
    public List<Restaurant> retrieveAll() {
        return restaurantService.retrieveAll();
    }

    @PostMapping("/api/restaurants")
    public void save(@RequestBody Restaurant restaurant) {
        restaurantService.save(restaurant);
    }

    @GetMapping("/api/restaurants/{id}")
    public Restaurant getById(@PathVariable long id) {
        return restaurantService.getById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }


    @PutMapping("/api/restaurants/vote/{id}")
    public void vote(@PathVariable long id, Principal principal) {
        restaurantService.vote(id, principal.getName());
    }

    @PutMapping("/api/restaurants/{id}")
    public Restaurant update(@RequestBody Restaurant newRestaurant, @PathVariable long id) {
        return restaurantService.getById(id)
                .map(restaurant -> {
                    restaurant.setName(newRestaurant.getName());
                    return restaurantService.save(restaurant);
                })
                .orElseThrow(() -> new RestaurantNotFoundException(id));

    }

    @DeleteMapping("/api/restaurants/{id}")
    public void delete(@PathVariable long id) {
        restaurantService.delete(id);
    }

}