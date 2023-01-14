package com.kruglov.websoka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kruglov.websoka.model.ChatRoom;

@Repository
public interface ChatRepository extends JpaRepository<ChatRoom, Long> {

    ChatRoom findByHostIdAndSlaveId(String hostId, String slaveId);
    
}
