package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping
    public List<RegisterDetails> getRegisterDetails() {
        return authService.getRegisterDetails();
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody RegisterDetails register) {
        return authService.addNewUser(register);
    }

    @PostMapping("/login")
    public String Login(@RequestBody RegisterDetails login) {
        return authService.authenticate(login);
    }
}