package com.jplopes.workshopspring.resources;


import com.jplopes.workshopspring.entity.Post;
import com.jplopes.workshopspring.resources.util.URL;
import com.jplopes.workshopspring.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostServices services;

    @GetMapping
    public ResponseEntity<List <Post>> findAll(){
        List<Post> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = services.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Post> list = services.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }




}
