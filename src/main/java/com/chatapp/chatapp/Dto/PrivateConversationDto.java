package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.PrivateConversation;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class PrivateConversationDto {

    private long id;

    private Long receiverId;

    private String receiverNick;

    private LocalDateTime lastChange;
    private Set<PrivateMessageDto> messages;

    public PrivateConversationDto(PrivateConversation privateConversation) {
        this.id = privateConversation.getId();
        this.receiverId = privateConversation.getReceiverId();
        this.receiverNick = privateConversation.getReceiverNick();
        this.lastChange = privateConversation.getLastChange();
        this.messages = privateConversation.getMessages().stream().map(privateMessage -> new PrivateMessageDto(privateMessage)).collect(Collectors.toSet());
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
}
