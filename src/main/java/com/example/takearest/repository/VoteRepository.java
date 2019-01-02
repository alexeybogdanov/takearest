package com.example.takearest.repository;

import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface VoteRepository extends CrudRepository<Vote,Long> {

    //check if particular row exist (1 = yes,  0 = no)
    long countByDateAndUser(LocalDate date, User user);

    Vote findVoteByDateAndUser(LocalDate date, User user);





}
