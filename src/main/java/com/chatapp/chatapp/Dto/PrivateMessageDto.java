package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.PrivateMessage;
import com.chatapp.chatapp.models.PrivateMessageStatus;
import com.chatapp.chatapp.models.PrivateMessageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrivateMessageDto {

    private long id;

    private LocalDateTime messageDate;

    private PrivateMessageType messageType;

    private PrivateMessageStatus messageStatus;

    private String message;


    public PrivateMessageDto(PrivateMessage privateMessage) {
        this.id = privateMessage.getId();
        this.messageDate = privateMessage.getMessageDate();
        this.messageType = privateMessage.getMessageType();
        this.messageStatus = privateMessage.getMessageStatus();
        this.message = privateMessage.getMessage();
    }


}
