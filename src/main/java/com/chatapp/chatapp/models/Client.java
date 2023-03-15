package com.chatapp.chatapp.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String nickName;

    private String email;

    private String password;

    private ClientRol clientRol;



    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Friend> friends= new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<PrivateConversation> privateConversations = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<GeneralChat> generalMessages = new HashSet<>();

    private ClientStatus clientStatus;


    public Client() {
    }

    public Client(String nickName, String email, String password) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.clientRol = ClientRol.USER;

    }
    public long getId() {
        return id;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientRol getClientRol() {
        return clientRol;
    }

    public void setClientRol(ClientRol clientRol) {
        this.clientRol = clientRol;
    }

    public Set<GeneralChat> getGeneralMessages() {
        return generalMessages;
    }

    public void setGeneralMessages(Set<GeneralChat> generalMessages) {
        this.generalMessages = generalMessages;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public Set<PrivateConversation> getPrivateConversations() {
        return privateConversations;
    }

    public void setPrivateConversations(Set<PrivateConversation> privateConversations) {
        this.privateConversations = privateConversations;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }
}
