package com.kruglov.websoka.exception;

public class ChatException extends Exception {
    private String message;

    public ChatException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
