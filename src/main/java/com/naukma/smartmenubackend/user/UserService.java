package com.naukma.smartmenubackend.user;

import com.naukma.smartmenubackend.user.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // BUSINESS LOGIC



    // CRUD OPERATIONS

    public Set<User> findAll() {
        return new HashSet<>(userRepo.findAll());
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmailEquals(email);
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    public void delete(User user) {
        userRepo.deleteById(user.getUserId());
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }
}
