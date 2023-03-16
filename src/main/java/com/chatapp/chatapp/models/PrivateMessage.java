package com.chatapp.chatapp.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PrivateMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="conversation")
    private PrivateConversation privateConversation;

    private LocalDateTime messageDate;

    private PrivateMessageType messageType;

    private PrivateMessageStatus messageStatus;

    private String message;



    public PrivateMessage() {
    }


    public PrivateMessage(LocalDateTime messageDate, PrivateMessageType messageType, PrivateMessageStatus messageStatus, String message) {

        this.messageDate = messageDate;
        this.messageType = messageType;
        this.messageStatus = messageStatus;
        this.message = message;
    }

    public long getId() {
        return id;
    }


    public PrivateConversation getPrivateConversation() {
        return privateConversation;
    }

    public void setPrivateConversation(PrivateConversation privateConversation) {
        this.privateConversation = privateConversation;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }

    public PrivateMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(PrivateMessageType messageType) {
        this.messageType = messageType;
    }

    public PrivateMessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(PrivateMessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
