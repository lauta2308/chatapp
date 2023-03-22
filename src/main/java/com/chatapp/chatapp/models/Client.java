package com.chatapp.chatapp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String nickName;

    private String email;

    private String password;

    private ClientRol clientRol;


    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Friend> friends= new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<PrivateConversation> privateConversations = new HashSet<>();

    @EqualsAndHashCode.Exclude
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

}
