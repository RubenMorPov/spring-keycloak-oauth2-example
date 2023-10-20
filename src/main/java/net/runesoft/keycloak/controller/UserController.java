package com.edw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(path = "/me")
    public ResponseEntity<HashMap<String, String>> me() {
        // get a successful user login
        OAuth2User user = ((OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new ResponseEntity<>(new HashMap<>() {{
            put("hello", user.getAttribute("name"));
            put("your email is", user.getAttribute("email"));
        }}, HttpStatus.OK);
    }
}