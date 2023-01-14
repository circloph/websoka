package com.kruglov.websoka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kruglov.websoka.model.ChatMessage;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
    
}
