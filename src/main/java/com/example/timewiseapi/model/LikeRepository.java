package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;

public interface LikeRepository {
    boolean existsByUserAndPost(User user, Community post);

    Like save(Like like);

    Like findByUserAndPost(User user, Community post);

    void delete(Like like);
}
