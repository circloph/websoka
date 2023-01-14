package com.kruglov.websoka.dto;

public class MessageResponse {

    private String text;

    public MessageResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
