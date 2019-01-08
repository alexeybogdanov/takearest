package com.example.takearest.controller;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.exception.RestaurantNotFoundException;
import com.example.takearest.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(RestaurantController.REST_URL)
public class RestaurantController {

    static final String REST_URL = "/api/restaurants";

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> find(@PathVariable long id) {
        return restaurantRepository.findById(id)
                .map(restaurant -> new ResponseEntity<>(restaurant, HttpStatus.OK))
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @GetMapping("search/by-name")
    public ResponseEntity<Restaurant> findByName(@RequestParam String name) {
        return restaurantRepository.findByName(name)
                .map(restaurant -> new ResponseEntity<>(restaurant, HttpStatus.OK))
                .orElseThrow(() -> new RestaurantNotFoundException(name));
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @Transactional
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = restaurantRepository.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newRestaurant.getId()).toUri();

        return ResponseEntity
                .created(uriOfNewResource)
                .body(newRestaurant);

    }

    @PutMapping("{id}")
    @Secured("ROLE_ADMIN")
    @Transactional
    public ResponseEntity<Restaurant> update(@RequestBody Restaurant newRestaurant, @PathVariable long id) {
        Restaurant updatedRestaurant = restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(newRestaurant.getName());
                    return restaurantRepository.save(restaurant);
                })
                .orElseGet(() -> restaurantRepository.save(newRestaurant));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(updatedRestaurant.getId()).toUri();

        return ResponseEntity
                .created(uriOfNewResource)
                .body(updatedRestaurant);
    }

    @DeleteMapping("{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if (!restaurantRepository.findById(id).isPresent()) {
            throw new RestaurantNotFoundException(id);
        }
        restaurantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Iterable<Restaurant> all() {
        return restaurantRepository.findAll();
    }

}