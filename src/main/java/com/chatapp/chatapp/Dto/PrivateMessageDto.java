package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.PrivateMessage;
import com.chatapp.chatapp.models.PrivateMessageType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrivateMessageDto {

    private long id;

    private LocalDateTime messageDate;

    private PrivateMessageType messageType;

    private String message;


    public PrivateMessageDto(PrivateMessage privateMessage) {
        this.id = privateMessage.getId();
        this.messageDate = privateMessage.getMessageDate();
        this.messageType = privateMessage.getMessageType();
        this.message = privateMessage.getMessage();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public PrivateMessageType getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }
}
