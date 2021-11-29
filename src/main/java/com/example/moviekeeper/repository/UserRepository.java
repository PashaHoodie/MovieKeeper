package com.example.moviekeeper.repository;

import com.example.moviekeeper.entity.user.Role;
import com.example.moviekeeper.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByUsername(String username);

    User getUserByUsername(String username);

    List<User> getByRole(Role role);
}
