package com.example.takearest.repository;

import com.example.takearest.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User getByUsername(String name);
}
