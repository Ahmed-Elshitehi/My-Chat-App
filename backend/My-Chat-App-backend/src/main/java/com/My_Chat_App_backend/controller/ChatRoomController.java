package com.My_Chat_App_backend.controller;


import com.My_Chat_App_backend.dto.MessageDto;
import com.My_Chat_App_backend.entity.ChatRoom;
import com.My_Chat_App_backend.entity.Message;
import com.My_Chat_App_backend.entity.User;
import com.My_Chat_App_backend.service.ChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatrooms")
@AllArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping({"", "/"})
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/user/{userId}")
    public List<ChatRoom> getChatRoomsByUserId(@PathVariable Long userId) {
        return chatRoomService.getChatRoomsByUserId(userId);
    }

    @GetMapping("/{chatRoomId}/participants")
    public List<User> getParticipantsInChatRoom(@PathVariable Long chatRoomId) {
        return chatRoomService.getParticipantsInChatRoom(chatRoomId);
    }

    @GetMapping("/{chatRoomId}/messages")
    public List<MessageDto> getMessagesInChatRoom(@PathVariable Long chatRoomId) {
        return chatRoomService.getMessagesInChatRoom(chatRoomId);
    }

    @PostMapping({"", "/"})
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomService.saveChatRoom(chatRoom);
    }

    @PostMapping("/{chatRoomId}/messages")
    public Message addMessageToChatRoom(@PathVariable Long chatRoomId, @RequestBody Message message) {
        return chatRoomService.createMessageInChatRoom(chatRoomId, message);
    }

    @PutMapping("/{chatRoomId}/addParticipants/{userId}")
    public ChatRoom addParticipantToChatRoom(@PathVariable Long chatRoomId, @PathVariable Long userId) {
        return chatRoomService.addParticipantToChatRoom(chatRoomId, userId);
    }

    @PutMapping("/{chatRoomId}")
    public ChatRoom updateChatRoomName(@PathVariable Long chatRoomId, @RequestParam String newName) {
        return chatRoomService.updateChatRoomName(chatRoomId, newName);
    }

    @DeleteMapping("/{chatRoomId}")
    public void deleteChatRoom(@PathVariable Long chatRoomId) {
        chatRoomService.deleteChatRoomById(chatRoomId);
    }

    @DeleteMapping("/{chatRoomId}/user/{userId}")
    public ChatRoom removeParticipantFromChatRoom(@PathVariable Long chatRoomId, @PathVariable Long userId) {
        return chatRoomService.removeParticipantFromChatRoom(chatRoomId, userId);
    }

}
