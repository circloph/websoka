<template>
  <div>
    <v-app style="background: #F0FFFF;">
    <v-navigation-drawer app
      v-model="drawer"
      absolute
      temporary
    >
    <v-list
    nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item nuxt to="/">
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>
              Home
            </v-list-item-title>
          </v-list-item>

          <span v-if="!isAuth">
          <v-list-item nuxt to="login">
            <v-list-item-icon>
              <v-icon>mdi-login</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Login
            </v-list-item-title>
          </v-list-item>
        </span>
        <span v-if="!isAuth">
          <v-list-item nuxt to="/registration">
            <v-list-item-icon>
              <v-icon>mdi-account-plus</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Registration</v-list-item-title>
          </v-list-item>
        </span>
          <span v-if="isAuth">
          <v-list-item nuxt to="/chat">
            <v-list-item-icon>
              <v-icon>mdi-forum</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Chat</v-list-item-title>
          </v-list-item>
        </span>
          <span v-if="isAuth">
          <v-list-item nuxt to="/single">
            <v-list-item-icon>
              <v-icon>mdi-forum</v-icon>
            </v-list-item-icon>
            <v-list-item-title>SingleChat</v-list-item-title>
          </v-list-item>
        </span>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar app
      absolute
      color="#43a047"
      dark
      shrink-on-scroll
      prominent
      fade-img-on-scroll
      scroll-target="#scrolling-techniques-5"
      scroll-threshold="500"
      height="95px"
    >
      <template v-slot:img="{ props }">
        <v-img
          v-bind="props"
          gradient="to top right, rgba(55,236,186,.7), rgba(25,32,72,.7)"
        ></v-img>
      </template>

      <v-app-bar-nav-icon @click="drawer = true">
      </v-app-bar-nav-icon>
      <img src="../static/l.png" alt="">
    </v-app-bar>
    <v-main>
      <Nuxt />
    </v-main>
    <v-footer class="footer" app>
      <p> 
          {{ isAuth }}
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
      username: '',
    }),
    computed: {
      isAuth: function () {
        return this.$store.getters['isAuth']
      },
    },
    created() {
      console.log('zhooopa');
    }
    
  }
</script>

<style scoped src="../assets/mainPage.css"></style>
