package com.example.dbTraceExample.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.dbTraceExample.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findById(Integer id);

    List<User> findByType(String type);
}
