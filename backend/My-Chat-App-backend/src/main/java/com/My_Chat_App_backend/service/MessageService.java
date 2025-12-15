package com.My_Chat_App_backend.service;

import com.My_Chat_App_backend.dto.MessageDto;
import com.My_Chat_App_backend.entity.Message;
import com.My_Chat_App_backend.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageDto getMessageById(Long id) {
        Message message = messageRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Message not found with id: " + id)
                );
        return mapToDto(message);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }


    public void deleteMessageById(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new RuntimeException("Message not found with id: " + id);
        }
        messageRepository.deleteById(id);
    }

    public MessageDto mapToDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .chatRoomId(message.getChatRoom().getId())
                .senderId(message.getSender().getId())
                .build();
    }

}
