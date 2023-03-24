


const { createApp } = Vue


createApp({
    data() {
      return {
        newNickName: "",
        generalChat: [],
        searchUser: "",
        searchFriends: "",
        clientsOnline: [],
        clientLogged: {},
        userMessage: "",
        userPrivateChats: [],
        displayPrivateChat: false,
        receiverId: "",
        receiverNick: "",
        messageColor: "",
        newGeneralMessage: false,
        privateChatArray: {},
        privateChatDisplayed: [] //messages


      }},
      created(){
     
        this.getCurrentClient();
  
        setInterval(this.getClientPrivateChats, 1000);
      
        setInterval(this.getGeneralChat, 1000);
        
        setInterval(this.getOnlineClients, 1000);
      
        
   

      },
      methods: {
        logout(){

          axios.patch("/api/clients/current/logout").then(response => {
            axios.post("/api/logout").then(response =>
              {
                window.location.href="../index.html";
              })
          })
        },
        showUserList(){

          this.$refs.userList.style.display = "block";
        },

        hideUserList(){
          this.$refs.userList.style.display = "none";
        },

        showNickForm(){
          this.$refs.nickForm.style.display = "flex";
          this.newNickName = "";
        },
        hideNickForm(){
          this.$refs.nickForm.style.display = "none";
        },

        changeNickName(){

          console.log(this.newNickName);
          if(this.newNickName.length > 0){

            axios.patch('/api/clients/current/nickname', `nickName=${this.newNickName}`).then(response => {
                console.log("name changed...")
              
    
            })
          }

        },
        getCurrentClient(){
          axios.get('/api/clients/current').then(response => {
          
            this.clientLogged = response.data;
            console.log(this.clientLogged);
            if(this.clientLogged.lastMessageColor !== null){
              this.mainChatMessageColor(this.clientLogged.lastMessageColor);
            } else {
              this.mainChatMessageColor("Blue");
            }
            

         


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


          test(){
            console.log("hi");

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
          
          console.log(chat);
          axios.get('/api/clients/current/getprivatechat' , {
            params: { receiverId: this.receiverId }
          }
          
          ).then(response => {


            console.log(response);
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

   
              axios.patch("/api/clients/current/readprivatemessages", 
              `receiverId=${this.receiverId}`
             
              )
            
          
          })

        
      

          
        },
        openMainChat(){
          
          this.displayPrivateChat = false;
          this.scrollChatToView();
       
        },

        mainChatMessageColor(color){
          console.log(color);
          this.messageColor = color;

          messageColorsArray = [this.$refs.messageColorRED, this.$refs.messageColorBLUE, this.$refs.messageColorYELLOW, this.$refs.messageColorGREEN, this.$refs.messageColorBROWN, this.$refs.messageColorORANGE, this.$refs.messageColorTEAL, this.$refs.messageColorGREY];


          messageColorsArray.forEach(element => element.style.border = "unset");
        
          
          let ref = `messageColor${color}`

          this.$refs[ref].style.border = "3px solid black";
       

    
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

            console.log(this.generalChat);

    
        }).then(response => {
          if(this.newGeneralMessage){
      
            this.scrollChatToView();
          }


        })
      
      
      
      },

      filterFriends(){

        if(this.searchFriends){
          this.searchFriends = false;
        } else {
          this.searchFriends = true;
        }

       
      },

    

        getOnlineClients() {

          if(this.searchFriends){
            axios.get('/api/clients/filterclients' , {
              params: { 
                
                nickName: this.searchUser,
                searchFriends: this.searchFriends
              }
            }).then(response => {
              this.clientsOnline = response.data;
            })
          }

          

          else {
            axios.get('/api/clients/chatclients').then(response => {

              
                this.clientsOnline = response.data;
              
           
                

            })
          }
        

    
     
      
        
              
      
          },

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
            
                let color;
                if(this.messageColor.length === 0){
                  color = 'BLUE';
                } else {
                  color = this.messageColor;
                }
               
  
                axios.post('/api/general', `message=${this.userMessage}&messageColor=${color}`).then(response => {
    
    
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