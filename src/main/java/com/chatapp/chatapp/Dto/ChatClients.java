package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.ClientStatus;
import com.chatapp.chatapp.models.FriendStatus;
import lombok.Data;

@Data
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


}
