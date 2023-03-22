package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.PrivateConversation;
import com.chatapp.chatapp.models.PrivateMessageStatus;
import com.chatapp.chatapp.services.ClientService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PrivateConversationDto {


    private long id;

    private Long receiverId;

    private String receiverNick;

    private LocalDateTime lastChange;


    private Set<PrivateMessageDto> messages;
    private long unreadMessages;

    public PrivateConversationDto(PrivateConversation privateConversation) {
        this.id = privateConversation.getId();
        this.receiverId = privateConversation.getReceiverId();
        this.lastChange = privateConversation.getLastChange();
        this.messages = privateConversation.getMessages().stream().map(privateMessage -> new PrivateMessageDto(privateMessage)).collect(Collectors.toSet());
        this.unreadMessages = this.messages.stream().filter(privateMessageDto -> privateMessageDto.getMessageStatus() == PrivateMessageStatus.NOT_READED).count();
    }


}
