package com.chatapp.chatapp.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PrivateConversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;


    private long receiverId;

    private String receiverNick;

    private LocalDateTime lastChange;



    @OneToMany(mappedBy="privateConversation", fetch=FetchType.EAGER)
    private Set<PrivateMessage> messages = new HashSet<>();;

    public PrivateConversation() {
    }

    public PrivateConversation(Client client, long receiver, String receiverNick) {
        this.client = client;
        this.receiverId = receiver;
        this.receiverNick = receiverNick;
    }

    public PrivateConversation(Client client, long receiver, String receiverNick, LocalDateTime lastChange) {
        this.client = client;
        this.receiverId = receiver;
        this.receiverNick = receiverNick;
        this.lastChange = lastChange;
    }

    public long getId() {
        return id;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverNick() {
        return receiverNick;
    }

    public void setReceiverNick(String receiverNick) {
        this.receiverNick = receiverNick;
    }

    public LocalDateTime getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDateTime lastChange) {
        this.lastChange = lastChange;
    }

    public Set<PrivateMessage> getMessages() {
        return messages;
    }

    public void setMessages(Set<PrivateMessage> messages) {
        this.messages = messages;
    }
}
