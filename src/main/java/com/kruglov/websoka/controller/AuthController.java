package com.kruglov.websoka.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kruglov.websoka.dto.EditProfileRequest;
import com.kruglov.websoka.dto.LoginRequest;
import com.kruglov.websoka.dto.UserResponse;
import com.kruglov.websoka.dto.RegistrationRequest;
import com.kruglov.websoka.exception.ChatException;
import com.kruglov.websoka.model.ChatMessage;
import com.kruglov.websoka.model.User;
import com.kruglov.websoka.security.JwtUser;
import com.kruglov.websoka.service.ChatService;
import com.kruglov.websoka.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) {
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
    public List<User> getDialogues() {
        return userService.getUsers();
    }

    @GetMapping("/messages")
    public List<ChatMessage> getMessages() {
        return chatService.getHistoryOfMessages();
    }

    @PutMapping("/users")
    public UserResponse editProfile(@RequestBody EditProfileRequest request, Authentication authentication) {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal(); 
        return userService.editProfile(jwtUser.getId(), request);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserResponse getUser(@PathVariable Long id, Principal principal) {
        return userService.getUser(id);
    }
}
