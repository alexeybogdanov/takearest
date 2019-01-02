package com.example.takearest.service.api;

import com.example.takearest.entity.Meal;

import java.util.Optional;

public interface MealService {

    Meal save(Meal meal);

    void delete(Long mealId);

    Optional<Meal> getById(long mealId);


}
