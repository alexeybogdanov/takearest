package com.example.takearest.service.api;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;

import java.time.LocalDate;

public interface VoteService {

    void vote(long restaurantId, String username);

    Vote findVoteByDateAndUser(LocalDate date, User user);
}
