package com.kruglov.websoka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kruglov.websoka.model.ChatRoom;
import com.kruglov.websoka.repository.ChatRepository;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public String getChatId(String senderId, String recipientId, boolean createdIfNotExist) {
        ChatRoom room = chatRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        if (room == null && createdIfNotExist) {
            ChatRoom newRoom = new ChatRoom(String.format("%s_%s", senderId, recipientId), senderId, recipientId);
            chatRepository.save(newRoom);
        }
        return chatRepository.findBySenderIdAndRecipientId(senderId, recipientId).getChatId();
    }
    
}
