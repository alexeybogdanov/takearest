package com.example.takearest.controller;

import com.example.takearest.entity.Meal;
import com.example.takearest.service.api.MealService;
import com.example.takearest.service.api.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class MealRestController {

    @Autowired
    private MealService mealService;

    @Autowired
    private RestaurantService restaurantService;

    @DeleteMapping("/api/meals/{id}")
    public void delete(@PathVariable long id) {
        mealService.delete(id);
    }

    @PostMapping("/api/meals")
    public void save(@RequestBody Map<String, String> body){
        String name = body.get("name");
        Long restaurant_id = Long.valueOf(body.get("restaurant_id"));
        Double price = Double.valueOf(body.get("price"));
        LocalDateTime timestamp = LocalDateTime.now();


        Meal meal = Meal.builder()
                .name(name)
                .price(price)
                .timestamp(timestamp)
                .restaurant(restaurantService.getById(restaurant_id).get())
                .build();

        mealService.save(meal);
        System.out.println("Meal Saved Successfully");
    }

}
