package com.kruglov.websoka.model.dto;

public class LoginResponse {

    private Long userId;
    private String login;
    private String role;
    private String token;
    
    public LoginResponse(Long userId, String login, String role, String token) {
        this.userId = userId;
        this.login = login;
        this.role = role;
        this.token = token;
    }


    public LoginResponse() {
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}
