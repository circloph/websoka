package com.kruglov.websoka.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kruglov.websoka.model.User;
import com.kruglov.websoka.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with name " + username + " not found");
        }
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new JwtUser(user.getId(), user.getLogin(), user.getPassword(), user.getName(), roles);
    }

    
}
