package com.kruglov.websoka.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kruglov.websoka.exception.ChatException;
import com.kruglov.websoka.model.User;
import com.kruglov.websoka.model.dto.LoginRequest;
import com.kruglov.websoka.model.dto.RegistrationRequest;
import com.kruglov.websoka.model.dto.LoginResponse;
import com.kruglov.websoka.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/registration") 
    public User registration(@RequestBody RegistrationRequest request) throws ChatException {
        return userService.registration(request);
    }

    @GetMapping("/lola")
    public String lola() {
        return "lolaaaa";
    }    

    @GetMapping("/dialogues")
    public Map<Long, String> getDialogues() {
        return userService.getUsers();
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Long id) {
        return userService.getUser(id);
    }
}
