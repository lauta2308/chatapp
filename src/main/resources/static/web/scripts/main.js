
const { createApp } = Vue

createApp({
    data() {
      return {
        loginEmail: "",
        loginPassword: "",
        registerNick: "",
        registerEmail: "",
        registerPassword: ""

      }},
      created(){
        console.log("hey!");


        
        
   

      },
      methods: {

        login() {
        
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
        
             
                
        
            })},

            register(){


              axios.post('/api/register', `nickName=${this.registerNick}&email=${this.registerEmail}&password=${this.registerPassword}`).then(response => {
        

                this.loginEmail = this.registerEmail;
                this.loginPassword = this.registerPassword;

                this.login();
      
                
        
            })

            }



      },
      computed: {
      
      }
    
    }).mount('#app')











   // axios.post('/api/clients', `nickName=${"lauta1010"}&email=${"lauta1010@test.com"}&password=${"12345"}`)