package com.kruglov.websoka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kruglov.websoka.dto.EditProfileRequest;
import com.kruglov.websoka.dto.LoginRequest;
import com.kruglov.websoka.dto.UserResponse;
import com.kruglov.websoka.dto.MessageRequest;
import com.kruglov.websoka.dto.MessageResponse;
import com.kruglov.websoka.dto.RegistrationRequest;
import com.kruglov.websoka.exception.ChatException;
import com.kruglov.websoka.model.Role;
import com.kruglov.websoka.model.User;
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

    public UserResponse login(LoginRequest request) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = userRepository.findByLogin(request.getLogin());
            String token = provider.createToken(authentication);
            return new UserResponse(user.getId(), user.getLogin(), user.getRole().getName(), token, user.getFirstname(), user.getLastname(), user.getStatus(), user.getBiography(), user.getPhotoname());
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

    public UserResponse getUser(Long id) {
        User findUser =  userRepository.findById(id).get();
        return new UserResponse(findUser.getId(), findUser.getLogin(), findUser.getRole().getName(), null, findUser.getFirstname(), findUser.getLastname()
            , findUser.getStatus(), findUser.getBiography(), findUser.getPhotoname());
    }

    public UserResponse editProfile(Long id, EditProfileRequest request) {
        User user =  userRepository.getReferenceById(id);
        if (user != null) {
            user.setFirstname(request.getFirstName());
            user.setLastname(request.getLastName());
            user.setStatus(request.getStatus());
            user.setBiography(request.getBiography());
            User savedUser = userRepository.save(user);
            return new UserResponse(savedUser.getId(), savedUser.getLogin(), savedUser.getRole().getName(), null, savedUser.getFirstname(), savedUser.getLastname()
            , savedUser.getStatus(), savedUser.getBiography(), savedUser.getPhotoname());
        }
        return null;
    }
}
