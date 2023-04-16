package com.jplopes.workshopspring.resources;

import com.jplopes.workshopspring.dto.UserDTO;
import com.jplopes.workshopspring.entity.User;

import com.jplopes.workshopspring.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserServices services;

    @GetMapping
    public ResponseEntity<List <UserDTO>> findAll(){
        List<User> list = services.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User obj = services.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

}
