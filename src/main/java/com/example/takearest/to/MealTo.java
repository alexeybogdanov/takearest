package com.example.takearest.to;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MealTo {


    //private long id;
    private String name;

    private double price;

    private LocalDate timestamp;

    private String  restaurant_id;

}
