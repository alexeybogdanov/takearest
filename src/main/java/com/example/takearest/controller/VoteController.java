package com.example.takearest.controller;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;
import com.example.takearest.exception.VoteNotFoundException;
import com.example.takearest.repository.RestaurantRepository;
import com.example.takearest.repository.UserRepository;
import com.example.takearest.service.api.VoteService;
import com.example.takearest.service.impl.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping(VoteController.REST_URL)
public class VoteController {

    static final String REST_URL = "/api/vote";

    private final VoteService voteService;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VoteController(VoteService voteService, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteService = voteService;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> vote(@PathVariable long id, Principal principal) {
        VoteServiceImpl.CustomVote newVote = voteService.vote(id, principal.getName());
        return newVote != null ? new ResponseEntity<>(newVote.getVote().getRestaurant(), newVote.getStatus())
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }

    @GetMapping("search/by-date")
    public ResponseEntity<Restaurant> getByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Principal principal) {

        User user = userRepository.getByUsername(principal.getName())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException(principal.getName()));

        Vote vote = voteService.findVoteByDateAndUser(date, user).orElseThrow(() -> new VoteNotFoundException(date.toString()));

        return restaurantRepository.findById(vote.getRestaurant().getId())
                .map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
