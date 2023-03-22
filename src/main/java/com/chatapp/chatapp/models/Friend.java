package com.chatapp.chatapp.models;

import com.chatapp.chatapp.Dto.ClientDto;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;


    private long friendId;

    private FriendStatus friendStatus;

    public Friend() {
    }

    public Friend(Client client, long friendId, FriendStatus friendStatus) {
        this.client = client;
        this.friendId = friendId;
        this.friendStatus = friendStatus;
    }


}
