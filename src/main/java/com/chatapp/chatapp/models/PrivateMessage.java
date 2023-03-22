package com.chatapp.chatapp.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
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


}
