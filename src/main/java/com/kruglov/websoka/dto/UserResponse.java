package com.kruglov.websoka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long userId;
    private String login;
    private String role;
    private String token;
    private String firstName;
    private String lastName;
    private String status;
    private String biography;
    private String photoName;

}
