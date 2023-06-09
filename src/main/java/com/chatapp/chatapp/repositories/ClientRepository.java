package com.chatapp.chatapp.repositories;

import com.chatapp.chatapp.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {


    Client findByEmail(String email);
    Client findByNickName(String nickName);
}
