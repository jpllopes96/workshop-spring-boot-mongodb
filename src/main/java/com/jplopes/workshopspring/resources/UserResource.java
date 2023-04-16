package com.jplopes.workshopspring.resources;

import com.jplopes.workshopspring.entity.User;

import com.jplopes.workshopspring.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserServices services;

    @GetMapping
    public ResponseEntity<List <User>> findAll(){
        List<User> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }
}
