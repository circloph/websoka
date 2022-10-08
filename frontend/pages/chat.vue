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
                @click="connect()"
                >Connect
            </v-btn>
        </div>
        <div id="window"> 
            <li v-for="message, index in messages" v-bind:key="index"> 
                        {{message}}
            </li>
        </div>
        <div class="inputChat">
            <v-text-field
            label="Message"
            hide-details="auto"
            light
            v-model="message"
            ></v-text-field>
        </div>
        <v-btn
            elevation="1"
            class="mx-"
            fab
            light
            color='#98FB98'
            @click="sendName()"
            >Send
        </v-btn>
    </div>  
</template>

<script>
    import ms from 'ms';
import * as SockJS from 'sockjs-client';
    import Stomp from "webstomp-client";


    export default {
      data: () => ({
        valid: false, 
        username: '',
        password: '',
        reponse: '',
        posts: '',
        message: '',
        messages: [],
        middleware: 'auth'
      }),
      methods: {
        connect() {
            this.socket = new SockJS("http://localhost:8080/hello");
            this.stompClient = Stomp.over(this.socket);
            this.stompClient.connect(
                {'Authorization': localStorage.getItem("token"), 
                'Sec-Fetch-Mode': 'no-cors',
                'Access-Control-Allow-Origin': '*'},
                frame => {
                this.connected = true;
                console.log(frame);
                this.stompClient.subscribe("/topic/logs", tick => {
                    this.messages.push(JSON.parse(tick.body).text)
                });
                },
                error => {
                console.log(error);
                this.connected = false;
                }
            );
        },
        sendName() {
            const msg = { message: this.message }
            console.log(JSON.stringify(msg))
            this.stompClient.send("/app/hello", JSON.stringify(msg), {})
        }
    }
}
</script>

    <style scoped src="../assets/chat.css"></style>