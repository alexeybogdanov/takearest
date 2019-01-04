package com.example.takearest.service.impl;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;
import com.example.takearest.repository.RestaurantRepository;
import com.example.takearest.repository.UserRepository;
import com.example.takearest.repository.VoteRepository;
import com.example.takearest.service.api.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    public void vote(long restaurantId, String username) {
        //TODO
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        LocalDateTime voteTime = LocalDateTime.now();
        LocalTime tooLate = LocalTime.of(22, 59, 00);


        // if time is before 11  user can vote

        //TODO
        User user = userRepository.getByUsername(username).get();
        if (voteTime.toLocalTime().isBefore(tooLate)
                && voteRepository.countByDateAndUser(voteTime.toLocalDate(), user) == 0) {
            Vote vote = new Vote();
            vote.setDate(voteTime.toLocalDate());
            vote.setRestaurant(restaurant);
            vote.setUser(user);
            voteRepository.save(vote);
        } else if ((voteTime.toLocalTime().isBefore(tooLate)
                && voteRepository.countByDateAndUser(voteTime.toLocalDate(), user) == 1)) {
            //TODO
            System.out.println("VOTE ========= " + voteRepository.findVoteByDateAndUser(voteTime.toLocalDate(), user).get().getId());
            //TODO
            Vote vote = voteRepository.findVoteByDateAndUser(voteTime.toLocalDate(), user).get();
            vote.setRestaurant(restaurant);
            voteRepository.save(vote);
        }

    }

    @Override
    public Optional<Vote> findVoteByDateAndUser(LocalDate date, User user) {
        return voteRepository.findVoteByDateAndUser(date, user);
    }
}
