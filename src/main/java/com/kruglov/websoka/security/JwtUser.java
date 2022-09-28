package com.kruglov.websoka.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails {

    private Long id;
    private String login;
    private String password;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id, String login, String password, String name,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
