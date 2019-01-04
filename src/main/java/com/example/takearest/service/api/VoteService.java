package com.example.takearest.service.api;

import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;

import java.time.LocalDate;
import java.util.Optional;

public interface VoteService {

    void vote(long restaurantId, String username);

    Optional<Vote> findVoteByDateAndUser(LocalDate date, User user);
}
