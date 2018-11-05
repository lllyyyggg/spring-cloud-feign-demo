package com.lanyage.eureka.consumerhello.controller;

import com.lanyage.eureka.consumerhello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<com.lanyage.eureka.commonapi.domain.User> userList() {
        return userService.findAll();
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public com.lanyage.eureka.commonapi.domain.User userDetails(@PathVariable("id") Integer id) {
        return userService.findOne(id);
    }
}
