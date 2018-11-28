package com.example.takearest.entity;

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

    @OneToMany(mappedBy="restaurant")
    private Set<Food> foodItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant() {
    }
}