package com.example.takearest.to;

import com.example.takearest.entity.Meal;
import com.example.takearest.entity.Restaurant;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RestaurantMenuTo {

    private Restaurant restaurant;

    private List<Meal> meals;
}
