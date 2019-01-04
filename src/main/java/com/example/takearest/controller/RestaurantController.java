package com.example.takearest.controller;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.exception.RestaurantNotFoundException;
import com.example.takearest.service.api.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
    public Resource<Restaurant> getById(@PathVariable long id) {
        Restaurant restaurant = restaurantService.getById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        return new Resource<>(restaurant,
                linkTo(methodOn(RestaurantController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(RestaurantController.class).retrieveAll()).withRel("restaurants"));
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