package com.example.takearest.repository;

import com.example.takearest.entity.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote,Long> {

}
