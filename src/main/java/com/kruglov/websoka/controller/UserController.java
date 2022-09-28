package com.kruglov.websoka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import com.kruglov.websoka.model.dto.MessageResponse;
import com.kruglov.websoka.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @MessageMapping("/hello")
    @SendTo("/topic/logs")
    public MessageResponse sendMessage(String message) {
        return userService.sendMessage(message);
    }

    @MessageExceptionHandler
    @SendTo("/topic/logs")
    public String handleException(AccessDeniedException exception) {
        return "Access denied";
    }
}
