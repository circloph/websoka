package com.kruglov.websoka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import com.kruglov.websoka.model.ChatMessage;
import com.kruglov.websoka.model.ChatNotification;
import com.kruglov.websoka.model.dto.MessageRequest;
import com.kruglov.websoka.model.dto.MessageResponse;
import com.kruglov.websoka.service.ChatService;
import com.kruglov.websoka.service.UserService;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @MessageMapping("/hello")
    @SendTo("/topic/logs")
    public MessageResponse sendMessage(MessageRequest message) {
        return userService.sendMessage(message);
    }

    @MessageExceptionHandler
    @SendTo("/topic/logs")
    public String handleException(AccessDeniedException exception) {
        return "Access denied";
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        String chatId = chatService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId);

        // ChatMessage saved = chatMessageService.save(chatMessage);
        
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/messages", "ZHOOOOOPA");
    }

}
