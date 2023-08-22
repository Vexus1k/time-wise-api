package com.example.timewiseapi.adapter;

import com.example.timewiseapi.model.Task;
import com.example.timewiseapi.model.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> {
}
