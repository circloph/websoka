<template>
    <div class="form">
    <v-form v-model="valid">
      <v-container fill-height>
        <v-row>
            <v-col
            cols="1"
              md="1"
              >
              <h3 style="margin:0px; padding:0px">
                Firstname
              </h3>
            </v-col>
            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
              light
              solo-inverted
              class="custom-placeholer-color"
                v-model="firstname"
                :counter="10"
                label="firstname"
                required
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col
            cols="1"
              md="1"
              >
              <h3 style="margin:0px; padding:0px">
                Login
              </h3>
            </v-col>
            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
              light
              solo-inverted
              class="custom-placeholer-color"
                v-model="login"
                :counter="10"
                label="login"
                required
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col
            cols="1"
              md="1"
              >
              <h3 style="margin:0px; padding:0px">
                Password
              </h3>
            </v-col>
            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
              light
              solo-inverted
              color="purple"
              filled
                v-model="password"
                :counter="10"
                label="password"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-btn
            elevation="2" @click="fetchSomething()"
          >Send</v-btn>
          </v-row>
        </v-container>
    </v-form>
  
  </div>
</template>

<script>
    export default {
      data: () => ({
        valid: false, 
        firstname: '',
        login: '',
        password: '',
        reponse: '',
        posts: '',
      }),
      methods:{
        fetchSomething() {
            this.$store.dispatch('registerUser', {
              name: this.firstname,
              login: this.login,
              password: this.password
            })
          this.$router.push('/login')
        }

      }
    }
</script>

<style scoped src="../assets/registration.css"></style>