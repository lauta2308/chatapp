package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.Friend;

public class FriendDto {

    private long id;

    public FriendDto(Friend friend) {
        this.id = friend.getId();
    }

    public long getId() {
        return id;
    }
}
