package com.naukma.smartmenubackend.user;

import com.naukma.smartmenubackend.user.model.UserCredentialsDTO;
import com.naukma.smartmenubackend.user.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> create(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCredentialsDTO));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> edit(@PathVariable("userId") Long userId,
                                          @RequestBody UserCredentialsDTO userCredentialsDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, userCredentialsDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
}
