package com.example.takearest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy="restaurant"/*, fetch = FetchType.EAGER*/)
    private Set<Food> foodItems;

    @Column
    private Long Vote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Food> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(Set<Food> foodItems) {
        this.foodItems = foodItems;
    }

    public Long getVote() {
        return Vote;
    }

    public void setVote(Long vote) {
        Vote = vote;
    }

    public Restaurant() {
    }
}