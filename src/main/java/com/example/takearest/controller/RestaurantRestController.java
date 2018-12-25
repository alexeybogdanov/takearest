package com.example.takearest.controller;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.service.api.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/api/restaurants")
    public List<Restaurant> retrieveAll() {
        return restaurantService.retrieveAll();
    }


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
    @ResponseBody
    public void vote(@RequestBody Map<String, String> body, Principal principal){
        Long restaurantId = Long.valueOf(body.get("id"));
//        Restaurant restaurant = new Restaurant();
//        restaurant.setName(name);
        //TODO  logged userID

        restaurantService.vote(restaurantId, principal.getName());
        System.out.println("Voted for Restaurant Successfully " + restaurantId + " " + restaurantService.getById(restaurantId).getName()
         + " By User " + principal.getName());
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