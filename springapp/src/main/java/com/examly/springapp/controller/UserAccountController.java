package com.examly.springapp.controller;

import com.examly.springapp.model.UserAccount;
import com.examly.springapp.repository.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    @Autowired
    private UserAccountRepo repo;

    @PostMapping
    public UserAccount createUser(@RequestBody UserAccount user) {
        return repo.save(user);
    }
}
