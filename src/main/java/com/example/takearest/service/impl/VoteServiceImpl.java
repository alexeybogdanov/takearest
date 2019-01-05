package com.example.takearest.service.impl;

import com.example.takearest.entity.Restaurant;
import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;
import com.example.takearest.exception.RestaurantNotFoundException;
import com.example.takearest.repository.RestaurantRepository;
import com.example.takearest.repository.UserRepository;
import com.example.takearest.repository.VoteRepository;
import com.example.takearest.service.api.VoteService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    private static final LocalTime EXPIRATION_TIME = LocalTime.of(22, 59, 00);

    private final VoteRepository voteRepository;

    private final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public CustomVote vote(long restaurantId, String username) {
        LocalDateTime voteTime = LocalDateTime.now();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException(username));

        if (voteTime.toLocalTime().isBefore(EXPIRATION_TIME)
                && voteRepository.countByDateAndUser(voteTime.toLocalDate(), user) == 0) {
            Vote vote = voteRepository.save(Vote.builder().date(voteTime.toLocalDate()).restaurant(restaurant).user(user).build());
            return new CustomVote(vote, HttpStatus.CREATED);

        }
        if ((voteTime.toLocalTime().isBefore(EXPIRATION_TIME)
                && voteRepository.countByDateAndUser(voteTime.toLocalDate(), user) == 1)) {

            return voteRepository.findVoteByDateAndUser(voteTime.toLocalDate(), user)
                    .map(vote -> {
                        vote.setRestaurant(restaurant);
                        return new CustomVote(voteRepository.save(vote), HttpStatus.OK);

                    })
                    .orElseThrow(RuntimeException::new);
        }
        return null;
    }

    @Data
    @AllArgsConstructor
    public static class CustomVote {
        private Vote vote;
        private HttpStatus status;

    }

    @Override
    public Optional<Vote> findVoteByDateAndUser(LocalDate date, User user) {
        return voteRepository.findVoteByDateAndUser(date, user);
    }
}
