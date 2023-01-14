<template>
    <div>
        <div class="dialogues">
            <p style="color:black; text-align:center;">Dialogues</p>
            <div class="dialogues_window">
                <li  v-bind:class="{ white: clicked==dialogues[index].id }" @click="changeStyle(index)"
                     v-for="dialog, index in dialogues" v-bind:key="index"> 
                    <span>{{dialog.firstname}} </span>    
                </li>
            </div>
        </div>


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
                       {{ message}}
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
        middleware: 'auth',
        dialogues: [],
        befDialogues: [],
        clicked: '',
        status: true,
      }),
      methods: {
        showResponse(response) {
            alert("----------------------------------------" + response + "----------------------------------------------------")
        },
        connect() {
            this.socket = new SockJS("http://localhost:8080/hello");
            this.stompClient = Stomp.over(this.socket);
            this.stompClient.connect(
                {'Authorization': localStorage.getItem("token"), 
                'Sec-Fetch-Mode': 'no-cors',
                'Access-Control-Allow-Origin': '*'},
                frame => {
                    this.connected = true;
                    this.stompClient.subscribe("/user/" + this.$store.getters.getUserId +"/queue/messages",
                        tick => {
                            console.log("subscribe")
                                console.log(tick.body)
                                console.log(tick)
                                this.messages.push(tick.body)
                            }
                    ),
                    console.log("chat_id " +  this.$store.getters.getUserId + "_" + this.clicked)
                    let id = ''
                    if (this.$store.getters.getUserId > this.clicked) {
                        id = this.$store.getters.getUserId + "_" + this.clicked
                    } else {
                        id = this.clicked + "_" + this.$store.getters.getUserId
                    }
                    console.log("chat Id = " + Number.isInteger(id))
                    this.messages = this.$store.dispatch('getMessages', { params: { id }})
                    this.messages = this.$store.getters.getContent
                    console.log("connect " + this.messages.content)
                    error => {
                        console.log(error);
                        this.connected = false;
                    }
            }
            );
            this.status = false;
        },
        changeStyle(index) {
            this.clicked = this.dialogues[index].id;
            console.log(this.clicked)
        },
        sendName() {
            const msg = {
                senderId: this.$store.getters.getUserId,
                recipientId: this.clicked,
                senderName: this.$store.getters.getUsername,
                recipientName: "test",
                content: this.message,
                timestamp: new Date(),
            }
            this.stompClient.send("/app/chat", JSON.stringify(msg), {})
        },
    },
    computed: {
        userId: function() {
            return this.$store.getters.getUserId
        }
    },
    async mounted() {
        this.befDialogues = await this.$axios.$get('http://localhost:8080/dialogues')
        console.log(this.befDialogues)
        this.dialogues =  this.befDialogues.filter((s) => s.firstname !== this.$store.getters.getFirstName)
        console.log(this.dialogues)
    }
}
</script>

    <style scoped src="../assets/chat.css"></style>
    <style scoped src="../assets/single.css"></style>

    <style>
        .white {
            background-color: rgb(148, 2, 2); 
        }
        .blue {
            background-color: blue;
        }
     
     
     </style>