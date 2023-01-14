package com.kruglov.websoka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kruglov.websoka.model.ChatMessage;
import com.kruglov.websoka.model.ChatRoom;
import com.kruglov.websoka.repository.ChatRepository;
import com.kruglov.websoka.repository.MessageRepository;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    public String getChatId(String senderId, String recipientId, boolean createdIfNotExist) {
        String hostId = null;
        String slaveId = null;

        if (Integer.parseInt(senderId) > Integer.parseInt(recipientId)) {
            hostId = senderId;
            slaveId = recipientId;
        } else {
            hostId = recipientId;
            slaveId = senderId;
        }
        ChatRoom room = chatRepository.findByHostIdAndSlaveId(hostId, slaveId);
        if (room == null && createdIfNotExist) {
            ChatRoom newRoom = null;
                newRoom = new ChatRoom(String.format("%s_%s", hostId, slaveId), hostId, slaveId);
            chatRepository.save(newRoom);

        }
        return chatRepository.findByHostIdAndSlaveId(hostId, slaveId).getChatId();
    }

    public ChatMessage saveMessage(ChatMessage message) {
        message.setSendername("popka");
        return messageRepository.save(message);
    }

    public List<ChatMessage> getHistoryOfMessages() {
        return messageRepository.findAll();
    }
    
}
