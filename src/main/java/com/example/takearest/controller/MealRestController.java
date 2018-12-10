package com.example.takearest.controller;

import com.example.takearest.entity.Meal;
import com.example.takearest.entity.Restaurant;
import com.example.takearest.service.MealService;
import com.example.takearest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
public class MealRestController {

    @Autowired
    private MealService mealService;

    @Autowired
    private RestaurantService restaurantService;

    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    @DeleteMapping("/api/meals/{mealId}")
    public void delete(@PathVariable(name = "mealId") Long mealId) {
        mealService.delete(mealId);
        System.out.println("Meal Deleted Successfully");
    }

    @PostMapping("/api/meals")
    public void saveRestaurant(@RequestBody Map<String, String> body){
        String name = body.get("name");
        Long restaurant_id = Long.valueOf(body.get("restaurant_id"));
        Double price = Double.valueOf(body.get("price"));

        Meal meal = new Meal();
        meal.setName(name);
        meal.setPrice(price);
        meal.setRestaurant(restaurantService.getById(restaurant_id));
        mealService.save(meal);
        System.out.println("Meal Saved Successfully");
    }

}
