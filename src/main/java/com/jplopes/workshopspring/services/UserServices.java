package com.jplopes.workshopspring.services;
import com.jplopes.workshopspring.entity.User;
import com.jplopes.workshopspring.repository.UserRepository;
import com.jplopes.workshopspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id){
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("User not found"));
    }

}
