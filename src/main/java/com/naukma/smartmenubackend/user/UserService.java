package com.naukma.smartmenubackend.user;

import com.naukma.smartmenubackend.exception.InvalidUserDataException;
import com.naukma.smartmenubackend.user.model.UserCredentialsDTO;
import com.naukma.smartmenubackend.user.model.User;
import com.naukma.smartmenubackend.user.model.UserDTO;
import com.naukma.smartmenubackend.utils.DTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.naukma.smartmenubackend.utils.Utils.isNullOrEmpty;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // BUSINESS LOGIC

    public UserDTO createUser(UserCredentialsDTO userCredentialsDTO) {
        if (isNullOrEmpty(userCredentialsDTO.email())
                || isNullOrEmpty(userCredentialsDTO.password())
                || userCredentialsDTO.role() == null)
            throw new InvalidUserDataException("USER REQUIRED FIELD IS EMPTY");

        String encryptedPassword = new BCryptPasswordEncoder().encode(userCredentialsDTO.password());
        User user = new User(
                userCredentialsDTO.email(),
                encryptedPassword,
                userCredentialsDTO.role()
        );

        user = save(user);
        return DTOMapper.toDTO(user);
    }

    public UserDTO updateUser(Long userId, UserCredentialsDTO userCredentialsDTO) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("USER ID-%d NOT FOUND TO UPDATE", userId)));

        if (!isNullOrEmpty(userCredentialsDTO.email()))
            user.setEmail(userCredentialsDTO.email());
        if (!isNullOrEmpty(userCredentialsDTO.password())) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userCredentialsDTO.password());
            user.setPassword(encryptedPassword);
        }
        if (userCredentialsDTO.role() != null)
            user.setRole(userCredentialsDTO.role());

        user = save(user);
        return DTOMapper.toDTO(user);
    }

    public void deleteUser(Long userId) {
        User user = findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("USER ID-%d NOT FOUND TO DELETE", userId)));

        delete(user);
    }

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
