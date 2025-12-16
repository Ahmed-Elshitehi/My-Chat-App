package com.My_Chat_App_backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomDto {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private boolean isPrivate;
}
