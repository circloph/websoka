package com.kruglov.websoka.model.dto;

public class MessageRequest {

    private String message;

    public MessageRequest(String message) {
        this.message = message;
    }

    

    public MessageRequest() {
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
