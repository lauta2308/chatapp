package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.*;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDto {

    private long id;

    private String nickName;

    private ClientRol clientRol;

    private Set<PrivateConversationDto> privateConversations;

    private Set<FriendDto> friends;

    private Set<GeneralChatDto> generalMessages;

    private ClientStatus clientStatus;


    public ClientDto(Client client) {
        this.id = client.getId();

        this.nickName = client.getNickName();
        this.friends = client.getFriends().stream().map(friend -> new FriendDto(friend)).collect(Collectors.toSet());
        this.generalMessages = client.getGeneralMessages().stream().map(message -> new GeneralChatDto(message)).collect(Collectors.toSet());
        this.clientRol = client.getClientRol();
        this.privateConversations = client.getPrivateConversations().stream().map(privateConversation -> new PrivateConversationDto(privateConversation)).collect(Collectors.toSet());
        this.clientStatus = client.getClientStatus();
    }

    public long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public ClientRol getClientRol() {
        return clientRol;
    }

    public Set<FriendDto> getFriends() {
        return friends;
    }

    public Set<GeneralChatDto> getGeneralMessages() {
        return generalMessages;
    }


    public Set<PrivateConversationDto> getPrivateConversations() {
        return privateConversations;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }
}
