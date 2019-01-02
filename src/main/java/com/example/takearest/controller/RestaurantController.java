package com.example.takearest.controller;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.exception.RestaurantNotFoundException;
import com.example.takearest.service.api.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> retrieveAll() {
        return restaurantService.retrieveAll();
    }

    @PostMapping
    public void save(@RequestBody Restaurant restaurant) {
        restaurantService.save(restaurant);
    }

    @GetMapping("{id}")
    public Restaurant getById(@PathVariable long id) {
        return restaurantService.getById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @PutMapping("{id}")
    public Restaurant update(@RequestBody Restaurant newRestaurant, @PathVariable long id) {
        return restaurantService.getById(id)
                .map(restaurant -> {
                    restaurant.setName(newRestaurant.getName());
                    return restaurantService.save(restaurant);
                })
                .orElseThrow(() -> new RestaurantNotFoundException(id));

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        restaurantService.delete(id);
    }


}