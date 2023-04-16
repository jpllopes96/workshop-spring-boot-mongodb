package com.jplopes.workshopspring.services;

import com.jplopes.workshopspring.entity.Post;
import com.jplopes.workshopspring.repository.PostRepository;
import com.jplopes.workshopspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServices {
    @Autowired
    private PostRepository repo;

    public List<Post> findAll(){
        return repo.findAll();
    }

    public Post findById(String id){
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Post not found"));
    }



}
