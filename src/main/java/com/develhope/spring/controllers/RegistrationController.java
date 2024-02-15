/*
package com.develhope.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.develhope.spring.configurations.Role;
import com.develhope.spring.entities.typeOfUsers.User;
import com.develhope.spring.repositories.UserRepository;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    
    
  
    @PostMapping("/register")
    public String register(String username, String password, String role) {
        User user = new User();
        user.setFirstName(username);
        user.setPassword(password);
        user.setRole(Role.valueOf(role));

        userRepository.save(user);

        return "redirect:/login";
    }

}


*/