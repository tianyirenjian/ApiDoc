package com.tianyisoft.apidoc.repository;

import com.tianyisoft.apidoc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByName(String name);
    public Optional<User> findByToken(String token);
}
