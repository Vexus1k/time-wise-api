package com.example.timewiseapi.user;

import java.util.Optional;

public interface UserRepository {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  Optional<User> findByActivationToken(String activationToken);

  User save(User user);

  Optional<User> findById(Integer id);
}
