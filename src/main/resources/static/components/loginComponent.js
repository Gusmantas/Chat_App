import { router } from "../router.js"

export default{
    template:`
       <section class="loginSection">
            <h1 class="LogInPage">Welcome!</h1>
            <nav>
                <form class="loginForm" @submit.prevent="logIn">
                    <h3 class="h3LogIn">Log In:</h3>
                    <input class="inputFocus" v-model="username" type="text" placeholder="Enter username:" required>

                    <input class="inputFocus" v-model="password" type="text" :type="passwordType" placeholder="Enter password:" required>

                    <button type="button" class="showPasswordButton" @click=showOrHidePassword>{{ buttonText }}</button>
                    <button class="logInButton">Log In</button>
                </form>


            <p class="askForRegister">Not a member, yet? Please, <span class="registerWord" @click="$router.push('/register')"><a> register here </a> </span>to start a conversation!</p>
            </nav>
       </section>
    `,
    data(){
        return{
            username: '',
            password: '',
            passwordType: 'password',
            buttonText: 'Show password',
        }
    },
    computed: {

    },

    created(){
    },
    methods:{
      async logIn(){
        const url = '/rest/auth/signin'

        const userToLogin = {
          username: this.username,
          password: this.password
        }        

        try{
          const alreadyLoggedIn = JSON.parse(localStorage.getItem('accessToken'))
          const us = alreadyLoggedIn.user.username

          console.log('You are already logged in as: ' + us + '!\n Please log out before attempting another login!')
        } catch (e){
          try{
            let result = await fetch(url, {
            method:'POST',
            headers: {
              'Content-Type':'application/json'
            },
            body: JSON.stringify(userToLogin)
            })

            result = await result.json()
            console.log(result.error)

            if (!result.error){
              let user = await fetch('/rest/users/' + result.id)
              user = await user.json()
  
            console.log(user)
  
            const userAndToken = {
              user: user,
              token: result.accessToken
            }

            let currentUser = await fetch('/rest/users/' + userAndToken.user.id)
            currentUser = await currentUser.json()
            this.$store.commit('loginUser', currentUser)
            
            console.log(userAndToken)
            this.$store.commit('saveAccessToken', userAndToken)
            
            localStorage.setItem('readMessages', JSON.stringify([]))
            localStorage.setItem('readPrivateMessages', JSON.stringify([]))
            localStorage.setItem('userInModal', JSON.stringify({}))
            
          }

          } catch (e){
            console.log(e)
          }
        }
        router.push('/')          
      },

      showOrHidePassword(){
        if(this.passwordType === 'password') {
          this.passwordType = 'text'
          this.buttonText = 'Hide Password'
        } else {
          this.passwordType = 'password'
          this.buttonText = 'Show Password'
        }
      }

    },
}