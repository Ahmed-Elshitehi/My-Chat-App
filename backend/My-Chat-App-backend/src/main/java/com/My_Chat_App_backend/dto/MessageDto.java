package com.My_Chat_App_backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Long id;
    private String content;
    private LocalDateTime timestamp;

    private Long chatRoomId;

    private Long senderId;
}
