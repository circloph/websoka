<template>
<div>
    <div class="d-flex justify-center">
        <v-card
    class="mx-auto"
    max-width="600"
    width="500px"
    color="#F0FFFF"
  >
  <v-btn
      :disabled="disabled"
      class="button mx-2"
      fab
      dark
      color="indigo"

    >
      <v-icon dark>
        mdi-plus
      </v-icon>
    </v-btn>
      <v-img
      class="white--text align-end mx-auto"
      height="200px"
      width="200px"
      :src="require('../../static/no-avatar.png')"
    >
      <v-card-title>{{this.$store.getters.getUsername}}</v-card-title>
    </v-img>
    <v-input dark color="black" :disabled="disabled">
        
    </v-input>

    <v-text-field
    class="pl-8 pr-8"
    filled-full-width-label-top
    light
    solo-inverted
      label="Firstname"
      v-model="firstName"
      :disabled="disabled"
      outlined
      hint="Firstname"
      persistent-hint
    ></v-text-field>
    <v-text-field
    light
    class="pl-8 pr-8"
    solo-inverted 
      v-model="lastName" 
      outlined
      :disabled="disabled"
      hint="lastname"
      persistent-hint
      label="Lastname">
    </v-text-field>
    <v-text-field
    class="pl-8 pr-8"
    light
    solo-inverted 
      v-model="status" 
      outlined
      :disabled="disabled"
      hint="Status"
      persistent-hint
      label="Status">
    </v-text-field>
    <v-text-field
    class="pl-8 pr-8"
    light
    solo-inverted 
      v-model="biography" 
      outlined
      :disabled="disabled"
      hint="Biography"
      persistent-hint
      label="Biography">
    </v-text-field>
    <v-text-field
    class="pl-8 pr-8"
    light
    solo-inverted 
      v-model="role" 
      outlined
      disabled
      hint="Role"
      persistent-hint
      label="Role">
    </v-text-field>

    <v-card-actions>
      <v-btn
        color="black"
        text
        @click="changeProfile"
      >
        Edit
      </v-btn>

      <v-btn
        color="#4682B4"
        text
        @click="save"
      >
        Save
      </v-btn>
    </v-card-actions>
  </v-card>
      </div>
    <form method="POST" enctype="multipart/form-data"
        action="/upload">
        File to upload: <input type="file" name="file"><br /> Name: <input
            type="text" name="name"><br /> <br /> <input type="submit"
            value="Upload"> Press here to upload the file!
    </form>
    <br>
    <br>


</div>

</template>

<script>


export default {
  data: () => ({
    id: 0,
    firstName: '',
    lastName: '',
    status: '',
    biography: '',
    disabled: true,
    name: '',
    role: '',
    text: "text",
    isSelecting: false,
    selectedFile: null
  }),
  methods: {
        async getProfile() {
          console.log("getProfile" + this.$store.getters.getUserId)
          this.$store.dispatch('getProfile', {
            id: this.$store.getters.getUserId,
          })
          this.loadAllFromStore()
        },
        changeProfile() {
          this.disabled = !this.disabled;
        },
        loadAllFromStore() {
          
          this.id = this.$store.getters.getUserId
          this.firstName = this.$store.getters.getFirstName
          this.lastName = this.$store.getters.getLastName
          this.role = this.$store.getters.getRole.name
          this.status = this.$store.getters.getStatus
          this.biography = this.$store.getters.getBiography
      },
      save() {
        this.$store.dispatch('save', {
          firstName: this.firstName,
          lastName: this.lastName,
          status: this.status,
          biography: this.biography,
        })
        this.changeProfile()
        this.sendImage()
      },
      onFileChanged(e) {
        this.selectedFile = e.target.files[0];
      },
    },
    
    mounted() {
      this.getProfile();
    },
    computed: {
      checkDisabled() {
        return disabled;
      }
    }
  }
  
  
</script>

 
<style scoped src="../../assets/users.css"></style>
