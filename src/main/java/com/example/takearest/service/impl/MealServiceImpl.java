package com.example.takearest.service.impl;

import com.example.takearest.entity.Meal;
import com.example.takearest.repository.MealRepository;
import com.example.takearest.service.api.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    MealRepository mealRepository;

    @Override
    public void save(Meal meal) {
        mealRepository.save(meal);
    }

    @Override
    public void delete(Long mealId) {
        mealRepository.deleteById(mealId);
    }
}
