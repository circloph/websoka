package com.kruglov.websoka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import com.kruglov.websoka.dto.MessageRequest;
import com.kruglov.websoka.dto.MessageResponse;
import com.kruglov.websoka.model.ChatMessage;
import com.kruglov.websoka.service.ChatService;
import com.kruglov.websoka.service.UserService;

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
        // return userService.sendMessage(new MessageRequest(chatMessage.getContent()));
        // ChatMessage saved = chatMessageService.save(chatMessage);

        ChatMessage savedMessage = chatService.saveMessage(chatMessage);

        
        messagingTemplate.convertAndSendToUser(savedMessage.getRecipientId(),"/queue/messages", savedMessage);
        messagingTemplate.convertAndSendToUser(savedMessage.getSenderId(),"/queue/messages", savedMessage);
    }

}
