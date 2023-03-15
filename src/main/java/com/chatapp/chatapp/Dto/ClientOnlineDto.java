package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.Client;

public class ClientOnlineDto {

    private long id;

    private String nickName;

    public ClientOnlineDto(Client client) {
        this.id = client.getId();
        this.nickName = client.getNickName();
    }

    public long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }
}
