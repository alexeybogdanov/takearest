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


/**
 * @author JavaSolutionsGuide
 *
 */
@RestController
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/api/restaurants")
    public List<Restaurant> getEmployees() {
        List<Restaurant> restaurants = restaurantService.retrieveRestaurants();
        return restaurants;
    }

    @GetMapping("/api/restaurant/{restaurantId}")
    public Set<Meal> getRestaurantMeals(@PathVariable(name="restaurantId")Long restaurantId) {

        return restaurantService.getRestaurantMeals(restaurantId);
    }

    @PostMapping("/api/restaurants")
    public void saveRestaurant(@RequestBody Map<String, String> body){
        String name = body.get("name");
//        String foodItems =  body.get("foodItems");
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
//        restaurant.setFoodItems(new HashSet<String>(foodItems));
        restaurantService.saveRestaurant(restaurant);
        System.out.println("Restaurant Saved Successfully");
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