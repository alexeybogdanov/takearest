package com.example.takearest.controller;

import com.example.takearest.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class MealRestController {

    @Autowired
    private MealService mealService;

    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    @DeleteMapping("/api/meals/{mealId}")
    public void delete(@PathVariable(name = "mealId") Long mealId) {
        mealService.delete(mealId);
        System.out.println("Meal Deleted Successfully");
    }

}
