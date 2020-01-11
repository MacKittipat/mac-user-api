package com.mackittipat.userapi.controller;

import com.mackittipat.userapi.dto.User;
import com.mackittipat.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public Iterable<User> findUsers() {
        return userRepository.findAll();
    }

    @GetMapping("users/admins")
    public Iterable<User> findAdmins() {
        return userRepository.findAll();
    }

}
