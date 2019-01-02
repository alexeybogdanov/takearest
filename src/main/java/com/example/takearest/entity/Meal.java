package com.example.takearest.entity;

import com.example.takearest.to.MealTo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude="restaurant")
@Table(name="meal")
public class Meal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @Column(name = "timestamp")
    private LocalDate timestamp;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    @JsonBackReference
    private Restaurant restaurant;

    public Meal(MealTo mealTo,LocalDate timestamp, Restaurant restaurant) {
        this.name = mealTo.getName();
        this.price = mealTo.getPrice();
        this.timestamp = timestamp;
        this.restaurant = restaurant;
    }





}
