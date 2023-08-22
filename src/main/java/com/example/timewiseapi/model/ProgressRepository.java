package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository {
    Progress save(Progress progress);

    List<Progress> findByUser(User user);

    void deleteById(int progressId);

    List<Progress> findByGoalId(int goalId);

    void delete(Progress progress);

    List<Progress> findAll();
}
