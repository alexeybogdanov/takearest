package com.example.takearest.service.impl;

import com.example.takearest.entity.Meal;
import com.example.takearest.repository.MealRepository;
import com.example.takearest.service.api.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    MealRepository mealRepository;

    @Override
    public Optional<Meal> getById(long mealId) {
        return mealRepository.findById(mealId);
    }

    @Override
    @Transactional
    public Meal save(Meal meal) {
       return mealRepository.save(meal);
    }

    @Override
    @Transactional
    public void delete(Long mealId) {
        mealRepository.deleteById(mealId);
    }
}
