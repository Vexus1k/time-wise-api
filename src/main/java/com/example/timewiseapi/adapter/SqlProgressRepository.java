package com.example.timewiseapi.adapter;

import com.example.timewiseapi.model.Progress;
import com.example.timewiseapi.model.ProgressRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlProgressRepository extends ProgressRepository, JpaRepository<Progress, Integer> {

}
