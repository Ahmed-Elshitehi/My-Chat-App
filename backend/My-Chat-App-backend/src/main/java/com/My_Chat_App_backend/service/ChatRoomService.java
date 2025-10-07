package com.My_Chat_App_backend.service;

import com.My_Chat_App_backend.entity.ChatRoom;
import com.My_Chat_App_backend.entity.Message;
import com.My_Chat_App_backend.entity.User;
import com.My_Chat_App_backend.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;
    private final MessageService messageService;

    public ChatRoom getChatRoomById(Long id) {
        return chatRoomRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Chat room not found with id: " + id));
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public List<ChatRoom> getChatRoomsByUserId(Long userId) {
        return chatRoomRepository.findByParticipants_Id(userId);
    }

    public ChatRoom saveChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public void deleteChatRoomById(Long chatRoomId) {
        if (!chatRoomRepository.existsById(chatRoomId)) {
            throw new RuntimeException("Chat room not found with id: " + chatRoomId);
        }
        chatRoomRepository.deleteById(chatRoomId);
    }

    public ChatRoom addParticipantToChatRoom(Long chatRoomId, Long userId) {
        ChatRoom chatRoom = getChatRoomById(chatRoomId);
        User user = userService.getUserById(userId);
        chatRoom.getParticipants().add(user);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    public List<User> getParticipantsInChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = getChatRoomById(chatRoomId);
        return chatRoom.getParticipants();
    }

    public List<Message> getMessagesInChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = getChatRoomById(chatRoomId);
        return chatRoom.getMessages();
    }

    public ChatRoom updateChatRoomName(Long chatRoomId, String newName) {
        ChatRoom chatRoom = getChatRoomById(chatRoomId);
        chatRoom.setName(newName);
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom removeParticipantFromChatRoom(Long chatRoomId, Long userId) {
        ChatRoom chatRoom = getChatRoomById(chatRoomId);
        User user = userService.getUserById(userId);
        chatRoom.getParticipants().remove(user);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    public Message createMessageInChatRoom(Long chatRoomId, Message message) {
        ChatRoom chatRoom = getChatRoomById(chatRoomId);
        message.setChatRoom(chatRoom);
        Message savedMessage = messageService.saveMessage(message);
        chatRoom.getMessages().add(savedMessage);
        chatRoomRepository.save(chatRoom);
        return savedMessage;
    }

}
