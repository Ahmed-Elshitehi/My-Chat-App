package com.My_Chat_App_backend.controller;

import com.My_Chat_App_backend.entity.User;
import com.My_Chat_App_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/search/username/{usernamePart}")
    public ResponseEntity<List<User>> searchUsersByUsername(@PathVariable String usernamePart) {
        return ResponseEntity.ok(userService.searchUsersByUsername(usernamePart));
    }

    @GetMapping("/search/email/{emailPart}")
    public ResponseEntity<List<User>> searchUsersByEmail(@PathVariable String emailPart) {
        return ResponseEntity.ok(userService.searchUsersByEmail(emailPart));
    }

    @GetMapping("/search/{part}")
    public ResponseEntity<List<User>> searchUsers(@PathVariable String part) {
        Set<User> userSet = new HashSet<>();
        userSet.addAll(userService.searchUsersByEmail(part));
        userSet.addAll(userService.searchUsersByUsername(part));
        return ResponseEntity.ok(new ArrayList<>(userSet));
    }

    @PostMapping({"", "/"})
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
