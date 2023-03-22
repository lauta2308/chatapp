package com.chatapp.chatapp.models;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class GeneralChat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String message;

    private LocalDateTime messageDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    public GeneralChat() {
    }

    public GeneralChat(String message, LocalDateTime messageDate, Client client) {
        this.message = message;
        this.messageDate = messageDate;
        this.client = client;
    }


}
