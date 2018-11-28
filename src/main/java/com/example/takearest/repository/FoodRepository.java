package com.example.takearest.repository;

import com.example.takearest.entity.Food;
import com.example.takearest.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long>{

}
