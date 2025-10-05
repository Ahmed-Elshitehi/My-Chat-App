package com.My_Chat_App_backend.repository;

import com.My_Chat_App_backend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatRoom_IdOrderByTimestampAsc(Long chatRoomId);
}
