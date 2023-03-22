package com.chatapp.chatapp.Dto;

import com.chatapp.chatapp.models.GeneralChat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GeneralChatDto {

    private long id;

    private String nickName;
    private String message;

    private LocalDateTime messageDate;



    public GeneralChatDto() {
    }

    public GeneralChatDto(GeneralChat generalChat) {
        this.id = generalChat.getId();
        this.nickName = generalChat.getClient().getNickName();
        this.message = generalChat.getMessage();
        this.messageDate = generalChat.getMessageDate();
    }


}
