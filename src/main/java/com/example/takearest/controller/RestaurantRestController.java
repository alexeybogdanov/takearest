package com.example.takearest.controller;

import java.util.List;
import java.util.Set;

import com.example.takearest.entity.Food;
import com.example.takearest.entity.Restaurant;
import com.example.takearest.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
    public Set<Food> getRestaurant(@PathVariable(name="restaurantId")Long restaurantId) {

        return restaurantService.getRestaurant(restaurantId);
    }

//    @PostMapping("/api/employees")
//    public void saveEmployee(Employee employee){
//        restaurantService.saveEmployee(employee);
//        System.out.println("Employee Saved Successfully");
//    }
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