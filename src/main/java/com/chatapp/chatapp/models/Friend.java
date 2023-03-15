package com.chatapp.chatapp.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    private FriendStatus friendStatus;

    public Friend() {
    }

    public Friend(Client client, FriendStatus friendStatus) {
        this.client = client;
        this.friendStatus = friendStatus;
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

    public FriendStatus getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(FriendStatus friendStatus) {
        this.friendStatus = friendStatus;
    }
}
