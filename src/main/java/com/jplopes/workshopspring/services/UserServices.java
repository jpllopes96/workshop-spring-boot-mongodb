package com.jplopes.workshopspring.services;
import com.jplopes.workshopspring.dto.UserDTO;
import com.jplopes.workshopspring.entity.User;
import com.jplopes.workshopspring.repository.UserRepository;
import com.jplopes.workshopspring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public User insert (User obj){
        return repo.insert(obj);
    }

    public void delete(String id){
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj){
        Optional<User> obj1 = repo.findById(obj.getId());
        User newObj = obj1.orElseThrow(() -> new ObjectNotFoundException("User not found"));
        updateData(newObj, obj);
        return repo.save(newObj);

    }


    public void updateData(User entity, User obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }


}
