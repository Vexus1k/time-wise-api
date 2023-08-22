package com.example.timewiseapi.adapter;

import com.example.timewiseapi.model.Goal;
import com.example.timewiseapi.model.GoalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlGoalRepository extends GoalRepository, JpaRepository<Goal, Integer> {
}
