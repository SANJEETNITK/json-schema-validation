package com.springboot.redisexample.controller;

import com.springboot.redisexample.entity.User;
import com.springboot.redisexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        if (userService.saveUser(user)) {
            return ResponseEntity.ok("User saved");
        } else {
            return ResponseEntity.internalServerError().body("Could not save user");
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping("/user")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        if (userService.updateUser(user)) {
            return ResponseEntity.ok("User updated");
        } else {
            return ResponseEntity.internalServerError().body("Could not update user");
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        if (userService.deleteUserById(id)) {
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.internalServerError().body("Could not delete user");
        }
    }

}
