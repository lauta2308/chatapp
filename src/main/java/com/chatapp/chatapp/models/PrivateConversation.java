package com.chatapp.chatapp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class PrivateConversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;


    private long receiverId;



    private LocalDateTime lastChange;


    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="privateConversation", fetch=FetchType.EAGER)
    private Set<PrivateMessage> messages = new HashSet<>();;

    public PrivateConversation() {
    }

    public PrivateConversation(Client client, long receiver) {
        this.client = client;
        this.receiverId = receiver;

    }

    public PrivateConversation(Client client, long receiver, LocalDateTime lastChange) {
        this.client = client;
        this.receiverId = receiver;

        this.lastChange = lastChange;
    }


}
