package com.tianyisoft.apidoc.service;

import com.tianyisoft.apidoc.domain.User;
import com.tianyisoft.apidoc.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(String username, String password) {
        User user = userRepository.findByName(username);
        if (user != null) {
            try {
                if (BCrypt.checkpw(password, user.getPass())) {
                    while (true) {
                        String token = UUID.randomUUID().toString();
                        if (!userRepository.findByToken(token).isPresent()) {
                            user.setToken(token);
                            userRepository.save(user);
                            return token;
                        }
                    }
                }
            } catch (Exception ignored) {
                return StringUtils.EMPTY;
            }
            return StringUtils.EMPTY;
        }
        return StringUtils.EMPTY;
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
