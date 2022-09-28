package com.kruglov.websoka.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.kruglov.websoka.model.dto.LoginResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    MappingJackson2HttpMessageConverter httpMessageConverter;

    @Autowired
    private static SimpMessagingTemplate template;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
    AccessDeniedException accessDeniedException) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
            HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
            httpMessageConverter.write(new LoginResponse(), MediaType.APPLICATION_JSON, outputMessage);
            template.convertAndSend("/topic/logs", "please login");
    }
    
}

