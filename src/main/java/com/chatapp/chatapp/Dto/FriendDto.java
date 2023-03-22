package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.Friend;
import com.chatapp.chatapp.models.FriendStatus;
import lombok.Data;

@Data
public class FriendDto {

    private long id;

    private long friendId;

    private FriendStatus friendStatus;



    public FriendDto(Friend friend) {
        this.id = friend.getId();
        this.friendId = friend.getId();
        this.friendStatus = friend.getFriendStatus();
    }


}
