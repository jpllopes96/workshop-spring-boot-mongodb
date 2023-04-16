package com.jplopes.workshopspring.resources;

import com.jplopes.workshopspring.dto.UserDTO;
import com.jplopes.workshopspring.entity.User;

import com.jplopes.workshopspring.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
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

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserDTO objDto){
        User obj = services.fromDTO(objDto);
        obj = services.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
       services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@RequestBody UserDTO objDto, @PathVariable String id){
        User obj = services.fromDTO(objDto);
        obj.setId(id);
        obj = services.update(obj);
        return ResponseEntity.noContent().build();
    }


}
