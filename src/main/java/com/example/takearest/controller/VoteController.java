package com.example.takearest.controller;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;
import com.example.takearest.repository.RestaurantRepository;
import com.example.takearest.repository.UserRepository;
import com.example.takearest.service.api.VoteService;
import com.example.takearest.to.RestaurantMenuTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/vote", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    @Autowired
    VoteService voteService;

    //TODO
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @PutMapping("{id}")
    public void vote(@PathVariable long id, Principal principal) {
        voteService.vote(id, principal.getName());
    }

    @GetMapping("search")
    public RestaurantMenuTo getByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Principal principal) {
        //TODO
        User user = userRepository.getByUsername(principal.getName()).get();
        Vote vote = voteService.findVoteByDateAndUser(date, user).get();

        System.out.println("------------------> rest name  = " + vote.getRestaurant().getName());
        //TODO
        Restaurant restaurant = restaurantRepository.getByName(vote.getRestaurant().getName()).get();
        return RestaurantMenuTo.builder().restaurant(restaurant).meals(restaurant.getMeals()).build();
    }
}
