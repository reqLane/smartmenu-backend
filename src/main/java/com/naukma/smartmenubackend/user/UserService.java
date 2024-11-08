package com.naukma.smartmenubackend.user;

import com.naukma.smartmenubackend.user.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // BUSINESS LOGIC

    // CRUD OPERATIONS

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmailEquals(email);
    }
}
