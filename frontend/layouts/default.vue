<template>
  <div>
    <v-app style="background: #F0FFFF;">
    <v-app-bar app color=#4682B4 height="65px">
    
      <v-btn color="#2F353B" to="/" nuxt> Home </v-btn>
        <v-btn class="leftMargin" color="#2F353B" v-if="getLogin" to="/single" nuxt> Single </v-btn><br>
        <v-spacer></v-spacer>
        <v-avatar v-if="getLogin" color="teal" size="45"></v-avatar>
        <v-btn clipped-right="true" class="leftMargin" color="#2F353B" v-if="getLogin" to="/users/${userId}"> {{login}} </v-btn>
        <v-btn clipped-right="true" class="leftMargin" color="#2F353B" v-if="getLogin" to="/lola"> Lola </v-btn>
        <v-btn class="leftMargin" color="#2F353B" v-if="!getLogin" to="/login" nuxt> Login </v-btn>
        <v-btn class="leftMargin" color="#2F353B" v-if="!getLogin" to="/registration" nuxt> Register </v-btn>

    </v-app-bar>

    <v-main>
      <Nuxt />
    </v-main>

    <v-footer class="footer" app>
      <p> 
        Права защищены
      </p>
    </v-footer>
  </v-app>
</div>
</template>

<script>
export default {

    data: () => ({
      drawer: false,
      group: null,
      login: '',
      status: false,
      test: false,
      userId: ''
    }),
    methods: {
      logout: function () {
        localStorage.clear()
        window.location.href = 'login';
      },
      getProfile: function() {
        this.$store.dispatch('getProfile', {
              id: this.userId
            })
      },
      async getInfoAboutUser(req, res) {
      }
    },
    computed: {
      isAuth() {
        return localStorage.getItem('token')
      },
      getLogin() {
        this.login = this.$store.getters.getLogin
        return this.login;
      },
      getUserId() {
        this.userId = this.$store.getters.getUserId
        return this.userId
      }

    },
  }
</script>

<style scoped src="../assets/mainPage.css"></style>
