package com.chatapp.chatapp.services.implementations;

import com.chatapp.chatapp.Dto.ClientDto;
import com.chatapp.chatapp.Dto.ChatClients;
import com.chatapp.chatapp.Dto.PrivateConversationDto;
import com.chatapp.chatapp.models.Client;
import com.chatapp.chatapp.models.ClientStatus;
import com.chatapp.chatapp.models.Friend;
import com.chatapp.chatapp.models.FriendStatus;
import com.chatapp.chatapp.repositories.ClientRepository;
import com.chatapp.chatapp.services.ClientService;
import com.chatapp.chatapp.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    FriendService friendService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream().map(client -> new ClientDto(client)).collect(Collectors.toList());
    }




    @Override
    public ClientDto getClient(Long id) {
        return new ClientDto(clientRepository.findById(id). orElse(null));
    }

    @Override
    public ResponseEntity<Object> registerClient(String nickName, String email, String password) {
        Client client = new Client(nickName, email, passwordEncoder.encode(password));
        clientRepository.save(client);
        return new ResponseEntity<>("Usuario creado con éxito", HttpStatus.CREATED);
    }

    @Override
    public ClientDto getCurrentClient(Authentication authentication) {
        return new ClientDto(clientRepository.findByEmail(authentication.getName()));
    }

    @Override
    public ResponseEntity<Object> clientLoggedStatus(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());

        client.setClientStatus(ClientStatus.ONLINE);
        clientRepository.save(client);
        return new ResponseEntity<>("User online status", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> clientLogoutStatus(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());

        client.setClientStatus(ClientStatus.OFFLINE);
        clientRepository.save(client);
        return new ResponseEntity<>("User offline status", HttpStatus.CREATED);
    }

    @Override
    public List<ChatClients> getChatClients(Authentication authentication) {
     List<Client> clients =  clientRepository.findAll();

     List <ChatClients> chatClients = clients.stream().map(client ->

         new ChatClients(client, friendService.checkfriend(authentication, client.getId()))

     ).collect(Collectors.toList());


     chatClients = chatClients.stream().sorted(Comparator.comparing(ChatClients::getNickName).reversed())
             .collect(Collectors.toList());
       return chatClients;
    }

    @Override
    public List<PrivateConversationDto> getCurrentPrivateConversations(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        ClientDto clientDto = new ClientDto(client);

        List <PrivateConversationDto> privateChatsList = new ArrayList<>();


        privateChatsList.addAll(clientDto.getPrivateConversations());

        privateChatsList = privateChatsList
                .stream()
                .sorted(Comparator.comparing(PrivateConversationDto::getLastChange).reversed())
                .collect(Collectors.toList());


        return privateChatsList;




    }

    @Override
    public List<ChatClients> filterOnlineClients(Authentication authentication, String nickName, Boolean searchFriends) {

        Client clientAuth = clientRepository.findByEmail(authentication.getName());
        List<Client> allClients = clientRepository.findAll();
        List<Friend> clientFriends = clientAuth.getFriends().stream().collect(Collectors.toList());
        List<Client> clientsList = new ArrayList<>();
        List<Friend> friendsList = new ArrayList<>();
        List<ChatClients> chatClientsList = new ArrayList<>();

        if(!nickName.isEmpty() && searchFriends.booleanValue() == true){


         friendsList =  clientFriends.stream().filter(friend -> friend.getClient().getNickName().toLowerCase().contains(nickName.toLowerCase())).collect(Collectors.toList());



            chatClientsList = friendsList.stream().map(friend ->


                    new ChatClients(clientRepository.findById(friend.getId()), friendService.checkfriend(authentication, client.getId()))

            ).collect(Collectors.toList());


        }


        List <ChatClients> chatClients = filterClients.stream().map(client ->

                new ChatClients(client, friendService.checkfriend(authentication, client.getId()))

        ).collect(Collectors.toList());


        chatClients = chatClients.stream().sorted(Comparator.comparing(ChatClients::getNickName).reversed())
                .collect(Collectors.toList());
        return chatClients;



    }


}
