package com.My_Chat_App_backend.repository;

import com.My_Chat_App_backend.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByParticipants_Id(Long userId);
}
