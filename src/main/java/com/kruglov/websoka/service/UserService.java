package com.kruglov.websoka.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kruglov.websoka.exception.ChatException;
import com.kruglov.websoka.model.Role;
import com.kruglov.websoka.model.User;
import com.kruglov.websoka.model.dto.LoginRequest;
import com.kruglov.websoka.model.dto.LoginResponse;
import com.kruglov.websoka.model.dto.MessageRequest;
import com.kruglov.websoka.model.dto.MessageResponse;
import com.kruglov.websoka.model.dto.RegistrationRequest;
import com.kruglov.websoka.repository.RoleRepository;
import com.kruglov.websoka.repository.UserRepository;
import com.kruglov.websoka.security.JwtTokenProvider;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenProvider provider;

    public MessageResponse sendMessage(MessageRequest message) {
        return new MessageResponse(message.getMessage());
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = userRepository.findByLogin(request.getLogin());
            String token = provider.createToken(authentication);
            return new LoginResponse(user.getId(), user.getLogin(), user.getRole().getName(), token);
        }
        return null;
    }

    public User registration(RegistrationRequest request) throws ChatException {
        if (userRepository.checkExistenceByLogin(request.getLogin())) {
            throw new ChatException("login busy");
        }
        Role role = roleRepository.findByName("ROLE_CLIENT");
        User user = new User(request.getName(), request.getLogin(), encoder.encode(request.getPassword()), role);

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }
}
