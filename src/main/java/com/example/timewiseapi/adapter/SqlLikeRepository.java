package com.example.timewiseapi.adapter;

import com.example.timewiseapi.model.Like;
import com.example.timewiseapi.model.LikeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlLikeRepository extends LikeRepository, JpaRepository<Like, Integer> {
}
