package com.kruglov.websoka.model.dto;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String name;
    private String login;
    private String password;
    
}
