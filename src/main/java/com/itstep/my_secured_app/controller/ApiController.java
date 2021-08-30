package com.itstep.my_secured_app.controller;

import com.itstep.my_secured_app.model.User;
import com.itstep.my_secured_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {
    private UserRepository userRepository;

    @GetMapping("/usernames")
    public List<String> getUsernames() {
        List<User> users = (List<User>) userRepository.findAll();
        List<String>usernames =  users.stream()
                .map(u -> u.getUsername())
                .collect(Collectors.toList());
        return usernames;
    }
}
