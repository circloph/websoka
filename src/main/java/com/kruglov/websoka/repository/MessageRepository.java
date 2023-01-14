package com.kruglov.websoka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kruglov.websoka.model.ChatMessage;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
    
    public List<ChatMessage> findByChatId(String chatId);

}
