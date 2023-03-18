package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.ClientStatus;
import com.chatapp.chatapp.models.FriendStatus;

public class ChatClients {

    private long id;

    private String nickName;

    private ClientStatus clientStatus;

    private FriendStatus friendStatus;

    public ChatClients(Client client, FriendStatus friendStatus) {
        this.id = client.getId();
        this.nickName = client.getNickName();
        this.clientStatus = client.getClientStatus();
        this.friendStatus = friendStatus;
    }

    public long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public FriendStatus getFriendStatus() {
        return friendStatus;
    }
}
