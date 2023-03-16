package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.PrivateConversation;
import com.chatapp.chatapp.models.PrivateMessageStatus;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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
        this.receiverNick = privateConversation.getReceiverNick();
        this.lastChange = privateConversation.getLastChange();
        this.messages = privateConversation.getMessages().stream().map(privateMessage -> new PrivateMessageDto(privateMessage)).collect(Collectors.toSet());
        this.unreadMessages = this.messages.stream().filter(privateMessageDto -> privateMessageDto.getMessageStatus() == PrivateMessageStatus.NOT_READED).count();
    }

    public long getId() {
        return id;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public String getReceiverNick() {
        return receiverNick;
    }

    public LocalDateTime getLastChange() {
        return lastChange;
    }

    public Set<PrivateMessageDto> getMessages() {
        return messages;
    }

    public long getUnreadMessages() {
        return unreadMessages;
    }
}
