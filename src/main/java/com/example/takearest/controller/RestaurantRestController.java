package com.example.takearest.controller;

import com.example.takearest.entity.Meal;
import com.example.takearest.entity.Restaurant;
import com.example.takearest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/api/restaurants")
    public List<Restaurant> retrieveAll() {
        return restaurantService.retrieveAll();
    }

//    @GetMapping("/api/restaurants/{restaurantId}")
//    public Set<Meal> getRestaurantMeals(@PathVariable(name="restaurantId")Long restaurantId) {
//
//        return restaurantService.getMeals(restaurantId);
//    }

    @GetMapping("/api/restaurants/{restaurantId}")
    public Restaurant getById(@PathVariable(name="restaurantId")Long restaurantId) {
        return restaurantService.getById(restaurantId);
    }

    @PostMapping("/api/restaurants")
    public void save(@RequestBody Map<String, String> body){
        String name = body.get("name");
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurantService.save(restaurant);
        System.out.println("Restaurant Saved Successfully");
    }

    @PostMapping("/api/restaurants/vote")
    public void vote(@RequestBody Map<String, String> body){
        Long restaurantId = Long.valueOf(body.get("id"));
//        Restaurant restaurant = new Restaurant();
//        restaurant.setName(name);
        restaurantService.vote(restaurantId);
        System.out.println("Voted for Restaurant Successfully " + restaurantId + " " + restaurantService.getById(restaurantId).getName());
    }
//
//    @DeleteMapping("/api/employees/{employeeId}")
//    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
//        restaurantService.deleteEmployee(employeeId);
//        System.out.println("Employee Deleted Successfully");
//    }
//
//    @PutMapping("/api/employees/{employeeId}")
//    public void updateEmployee(@RequestBody Employee employee,
//                               @PathVariable(name="employeeId")Long employeeId){
//        Employee emp = restaurantService.getEmployee(employeeId);
//        if(emp != null){
//            restaurantService.updateEmployee(employee);
//        }
//
//    }

}