package com.example.takearest.controller;

import com.example.takearest.entity.Meal;
import com.example.takearest.entity.Restaurant;
import com.example.takearest.exception.MealNotFoundException;
import com.example.takearest.exception.RestaurantNotFoundException;
import com.example.takearest.service.api.MealService;
import com.example.takearest.service.api.RestaurantService;
import com.example.takearest.to.MealTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/meals", produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController {

    @Autowired
    private MealService mealService;

    @Autowired
    private RestaurantService restaurantService;

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        mealService.delete(id);
    }

    @PostMapping
    public void save(@RequestBody MealTo mealTo) {
        long restaurant_id = Long.valueOf(mealTo.getRestaurant_id());
        LocalDate timestamp = LocalDate.now();
        Optional<Restaurant> result = restaurantService.getById(restaurant_id);
        if (result.isPresent()) {
            mealService.save(new Meal(mealTo, timestamp, result.get()));
        } else throw new RestaurantNotFoundException(restaurant_id);
    }

    @PutMapping("{id}")
    public Meal update(@RequestBody Meal newMeal, @PathVariable long id) {
        return mealService.getById(id)
                .map(meal -> {
                    meal.setName(newMeal.getName());
                    meal.setPrice(newMeal.getPrice());
                    return mealService.save(meal);
                })
                .orElseThrow(() -> new MealNotFoundException(id));

    }

}
