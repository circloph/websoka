<template>
    <div id="wrapper">
        <div class="button">
            <v-btn
                color="accent"
                depressed
                elevation="1"
                outlined
                rounded
                text
                x-small
                >Connect
            </v-btn>
        </div>
        <div id="window"> 
            <p id="status">
            </p>
            <ul class="list">

            </ul>
        </div>
        <!-- <form name="message" action=""> -->
            <!-- <v-input type="text" id="usermsg" size="63" placeholder="Type in something" /> -->
        <!-- </form> -->
        <div class="inputChat">
            <v-text-field
            label="Message"
            :rules="rules"
            hide-details="auto"
            light
            ></v-text-field>
        </div>
        <div class="button">
        <v-btn
            elevation="1"
            class="mx-"
            fab
            light
            color='#98FB98'
            >Send
        </v-btn>
    </div>
    </div>  
</template>

<script>
    export default {
    middleware: 'auth'
  }
</script>

<style scoped src="../assets/chat.css"></style>