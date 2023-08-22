package com.example.timewiseapi.model;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository {

    Community save(Community newPost);

    List<Community> findAll();

    Optional<Community> findById(Integer postId);
}
