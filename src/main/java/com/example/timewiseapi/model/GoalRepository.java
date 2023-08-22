package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;

import java.util.List;
import java.util.Optional;

public interface GoalRepository {
    Goal save(Goal goal);

    Optional<Goal> findById(int goalId);

    Goal findByIdAndUser(Integer id, User user);

    void delete(Goal goal);

    List<Goal> findAll();

}
