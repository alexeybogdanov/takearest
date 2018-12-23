package com.example.takearest.service.api;

import com.example.takearest.entity.Meal;

public interface MealService {

    void save(Meal meal);

    void delete(Long mealId);


}
