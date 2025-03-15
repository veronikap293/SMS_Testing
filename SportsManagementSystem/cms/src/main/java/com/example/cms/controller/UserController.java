package com.example.cms.controller;


import com.example.cms.controller.exceptions.UserNotFoundException;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // FIND ALL USERS
    @GetMapping("/users")
    List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    // RETRIEVE USER based on userId
//    @GetMapping("/users/{userId}")
//    User retrieveUser(@PathVariable("userId") Long userId) {
//        return repository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException(userId));
//    }
}


