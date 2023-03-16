
const { createApp } = Vue

createApp({
    data() {
      return {
        generalChat: [],
        clientsOnline: [],
        clientLogged: {},
        userMessage: "",
        userPrivateChats: [],
        displayPrivateChat: false,
        receiverId: "",
        receiverNick: "",
        newGeneralMessage: false,
        privateChatArray: {},
        privateChatDisplayed: [] //messages


      }},
      created(){
     
        setInterval(this.getCurrentClient, 1000);
        setInterval(this.getClientPrivateChats, 1000);
      
        setInterval(this.getGeneralChat, 1000);
        
        setInterval(this.getOnlineClients, 10000);
        this.testPrivateChat();
        
        
   

      },
      methods: {
        getCurrentClient(){
          axios.get('/api/clients/current').then(response => {
          
            this.clientLogged = response.data;
       
  

         


          })
        },

        getClientPrivateChats(){
          axios.get('/api/clients/current/privatechats').then(response => {


   
        

        this.userPrivateChats = response.data;
            
  
       
  

          
          
      
          

        })
        
        
      },

        checkPrivateChat(client){
      
          axios.get('/api/clients/current/checkprivatechat' , {
            params: { receiverId: client.id }
          }).then(response => {
            this.openPrivateChat(client);
          }).catch(e => {
            if(e.response.data === "Chat not found"){
              this.makePrivateChat(client);
            }
        
            
          })

        },
        makePrivateChat(client){
          axios.post('/api/clients/current/privatechat', `receiverId=${client.id}`).then(response => {
                this.checkPrivateChat(client);


          })},


          testPrivateChat(){
            axios.get('/api/clients/current/getprivatechat' , {
              params: { receiverId: 1}
            }
            
            ).then(response => {
              console.log(response.data);

            })

          },


        openPrivateChat(chat){

      
          if(chat){
            if(chat.receiverId > 0){
              this.receiverId = chat.receiverId;
            }         
            
           else if(chat.id> 0){
           
            this.receiverId = chat.id;
           }
          }
          

          axios.get('/api/clients/current/getprivatechat' , {
            params: { receiverId: this.receiverId }
          }
          
          ).then(response => {


          
            this.displayPrivateChat = true;
             this.receiverNick = response.data.receiverNick;
          
            this.receiverId = response.data.receiverId;
            this.privateChatArray = response.data;
            this.privateChatDisplayed = response.data.messages;


            this.privateChatDisplayed.forEach(element => {
              element.messageDate = new Date(element.messageDate);

              element.messageDate = `${element.messageDate.getMonth() + 1 } - ${element.messageDate.getDate()} ${element.messageDate.getHours()} : ${element.messageDate.getMinutes()} `

             
              
         
              
            });

            this.privateChatDisplayed = this.privateChatDisplayed.sort(function(a, b) { return a.id - b.id });
        
           


          }).then(response => {

              this.scrollChatToView();

              console.log(this.privateChatArray);
              axios.patch("/api/clients/current/readprivatemessages", 
              `receiverId=${this.receiverId}`
             
              )
            
          
          })

        
      

          
        },
        openMainChat(){
          
          this.displayPrivateChat = false;
          this.scrollChatToView();
       
        },
        getGeneralChat(){
          axios.get('/api/general').then(response => {
      

            if(response.data.length !== this.generalChat.length){
             
              this.generalChat = response.data;
           
           
              this.generalChat.forEach(element => {
                element.messageDate = new Date(element.messageDate);
  
                element.messageDate = `${element.messageDate.getMonth() + 1 } - ${element.messageDate.getDate()} ${element.messageDate.getHours()} : ${element.messageDate.getMinutes()} `
                
              });

              this.newGeneralMessage = true;
              this.generalChat = this.generalChat.sort(function(a, b) { return a.id - b.id });
       
             
            }

        

    
        }).then(response => {
          if(this.newGeneralMessage){
      
            this.scrollChatToView();
          }


        })
      
      
      
      },

        getOnlineClients() {
        
          axios.get('/api/clients/onlineClients').then(response => {

           response.data.length !== this.clientsOnline.length
              this.clientsOnline = response.data;
             

              this.clientsOnline.forEach(client => {


                axios.get('/api/clients/current/checkfriend' , {
                  params: { friendId: client.id }
                }).then(response => {
              
                  if(response.data === true){
            
                    
                    
                    client.friend = true;
                  } else {
             
                    client.friend = false;
                  }
                })

              })


      
    
            console.log(this.clientsOnline);
      
        
              
      
          })},

          addFriend(clientId){
         
            axios.post('/api/clients/current/addfriend', `friendId=${clientId}`).then(response => {
         
  
  
    
  
      })},

      deleteFriend(clientId){
        axios.delete('/api/clients/current/removefriend', 
        {
          params: { friendId: clientId }
        }).then(response => {
  
          

        })
        
      },
         





          sendMessage(){
            

            if(this.userMessage.length > 0){
              if(this.displayPrivateChat){
                this.sendPrivateMessage()
              }
  
              else {
                this.sendGeneralMessage();
              }
  
            }

           

          
            },

            
            sendPrivateMessage(){
           
              
            
  
                axios.post('/api/clients/current/privatemessage', `receiverId=${this.receiverId}&message=${this.userMessage}`).then(response => {
    
    
                    this.userMessage = ""
                    
                 
  
                  clearInterval(this.openPrivateChat);
                  this.openPrivateChat();
                    
                })
              
            },


            sendGeneralMessage(){
            
        
            
  
                axios.post('/api/general', `message=${this.userMessage}`).then(response => {
    
    
                    this.userMessage = ""
                    

                    
                   this.getGeneralChat()
                    
                })
              

            },

            scrollChatToView(){
           
              this.newGeneralMessage = false;
    
                this.$refs.lastMsg.scrollIntoView(true);

             
            },



          

      },
      computed: {
     
           
      


      
      }
    
    }).mount('#app')