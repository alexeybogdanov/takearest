package com.example.takearest.controller;

import com.example.takearest.entity.Meal;
import com.example.takearest.exception.MealNotFoundException;
import com.example.takearest.exception.RestaurantNotFoundException;
import com.example.takearest.repository.MealRepository;
import com.example.takearest.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(MealController.REST_URL)
public class MealController {

    static final String REST_URL = "/api/meals";

    private final MealRepository mealRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public MealController(MealRepository mealRepository, RestaurantRepository restaurantRepository) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Meal> save(@RequestBody Meal meal) {
        if (!restaurantRepository.findById(meal.getRestaurant().getId()).isPresent()) {
            throw new RestaurantNotFoundException(meal.getRestaurant().getId());
        }

        meal.setTimestamp(LocalDate.now());
        Meal newMeal = mealRepository.save(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newMeal.getId()).toUri();

        return ResponseEntity
                .created(uriOfNewResource)
                .body(newMeal);
    }

    @PostMapping("{id}")
    @Transactional
    public ResponseEntity<Meal> update(@RequestBody Meal newMeal, @PathVariable long id) {
        Meal updatedMeal = mealRepository.findById(id)
                .map(meal -> {
                    meal.setName(newMeal.getName());
                    meal.setPrice(newMeal.getPrice());
                    return mealRepository.save(meal);
                })
                .orElseThrow(() -> new MealNotFoundException(id));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(updatedMeal.getId()).toUri();

        return ResponseEntity
                .created(uriOfNewResource)
                .body(updatedMeal);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if (!mealRepository.findById(id).isPresent()) {
            throw new MealNotFoundException(id);
        }
        mealRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
