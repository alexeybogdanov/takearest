package com.example.takearest.repository;

import com.example.takearest.entity.User;
import com.example.takearest.entity.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends CrudRepository<Vote, Long> {

    /**
     * <p>This method checks if particular row exist in DB</p>
     *
     * @param date
     * @param user
     * @return 1 if row exist, 0 if row doesn't exist
     */
    long countByDateAndUser(LocalDate date, User user);

    Optional<Vote> findVoteByDateAndUser(LocalDate date, User user);


}
