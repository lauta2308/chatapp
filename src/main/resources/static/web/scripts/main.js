
const { createApp } = Vue

createApp({
    data() {
      return {
        loginEmail: "",
        loginPassword: "",
        registerNick: "",
        showLogInForm: true,
        showSignUpForm: false,
        errorNickName: "",
        errorLoginEmail: "",
        errorLoginPassword: "",
        errorLogin: "",

      }},
      created(){
        console.log("hey!");


        
        
   

      },
      methods: {
       
        displayLogInForm(){
          this.showLogInForm = true;
          this.showSignUpForm = false;

        },

       displaySignUpForm(){
        this.showLogInForm = false;
        this.showSignUpForm = true;
       },

       resetRegisterNickError(){
          this.errorNickName = "";
       },
       resetLoginEmailError(){
        this.errorLoginEmail = "";
        this.errorLogin = "";
       },

       checkRegisterNick(){
        if(this.registerNick.length === 0){
          this.errorNickName = "Write a NickName";
        } else {
          this.errorNickName = "";
        }
       },

       checkLoginEmail(){
        if(this.loginEmail.length === 0){
          this.errorLoginEmail = "Write an email"
        } else if(
          !this.loginEmail.includes("@")
        ) {
          this.errorLoginEmail = "Email should contain @"
        } else if(!this.loginEmail.includes(".com")){
          this.errorLoginEmail = "Not valid email"
        } 
        
        
        
        else{
          this.errorLoginEmail = "";
        }

       },

       resetLoginPasswordError(){
          this.errorLoginPassword = "";
          this.errorLogin = "";
       },

       checkLoginPassword(){
        if(this.errorLoginPassword.length === 0){
          this.errorLoginPassword = "Write a password";
        }

       },

  

        login() {
           if(this.loginEmail.length === 0 && this.loginPassword.length === 0){
            this.errorLogin = "Complete all fields to log in";
          }

          else if(this.loginEmail.length === 0 || this.loginPassword.length === 0){
            this.errorLogin = "Complete all fields to log in";
          } else{
            axios.post('/api/login', `email=${this.loginEmail}&password=${this.loginPassword}`).then(response => {

              console.log(response);

              if(response.status === 200){
                axios.patch('/api/clients/logged').then(response => {
                  this.loginEmail = "",
                  this.loginPassword = "",
                  this.registerNick = "",
                  this.registerEmail = "",
                  this.registerPassword = ""
                    window.location.href="./web/mainchat.html";
                    }
                    )
              }
      
           
              
      
          })
          }
        
            },

            register(){

              if(this.loginEmail.length === 0 && this.loginPassword.length === 0){
                this.errorLogin = "Complete all fields to Sign Up";
              }
    
              else if(this.loginEmail.length === 0 || this.loginPassword.length === 0){
                this.errorLogin = "Complete all fields to Sign Up";
              } else {

                axios.post('/api/register', `nickName=${this.registerNick}&email=${this.loginEmail}&password=${this.loginPassword}`).then(response => {
        


                  this.login();
        
                  
          
              })
              }


            }



      },
      computed: {
      
      }
    
    }).mount('#app')






