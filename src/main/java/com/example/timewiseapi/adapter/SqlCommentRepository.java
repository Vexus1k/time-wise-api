package com.example.timewiseapi.adapter;

import com.example.timewiseapi.model.Comment;
import com.example.timewiseapi.model.CommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Integer> {
}
