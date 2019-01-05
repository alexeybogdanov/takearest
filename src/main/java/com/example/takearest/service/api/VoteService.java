package com.example.takearest.service.api;

import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;
import com.example.takearest.service.impl.VoteServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

public interface VoteService {

    VoteServiceImpl.CustomVote vote(long restaurantId, String username);

    Optional<Vote> findVoteByDateAndUser(LocalDate date, User user);
}
