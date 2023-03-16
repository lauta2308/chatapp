package com.chatapp.chatapp.services;

import com.chatapp.chatapp.Dto.PrivateConversationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface PrivateConversationService {
    ResponseEntity<Object> checkprivatechat(Authentication authentication, long receiverId);
    PrivateConversationDto getPrivateConversation(Authentication authentication, long receiverId);

    ResponseEntity<Object> makeprivatechat(Authentication authentication, long receiverId);


}
