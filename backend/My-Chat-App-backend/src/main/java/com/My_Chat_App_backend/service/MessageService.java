package com.My_Chat_App_backend.service;

import com.My_Chat_App_backend.entity.Message;
import com.My_Chat_App_backend.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class MessageService {
    MessageRepository messageRepository;

    public Message getMessageById(Long id) {
        return messageRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
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

}
