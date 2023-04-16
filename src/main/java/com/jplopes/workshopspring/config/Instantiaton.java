package com.jplopes.workshopspring.config;

import com.jplopes.workshopspring.dto.AuthorDTO;
import com.jplopes.workshopspring.dto.CommentDTO;
import com.jplopes.workshopspring.entity.Post;
import com.jplopes.workshopspring.entity.User;
import com.jplopes.workshopspring.repository.PostRepository;
import com.jplopes.workshopspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class Instantiaton implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

      @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, Instant.parse("2019-06-20T19:53:07Z"), "Lets go travel", "I'm going to travel to SP, kisses!", new AuthorDTO(maria));
        Post post2 = new Post(null, Instant.parse("2019-06-23T10:53:07Z"), "Good Morning", "I'm happy today!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Have a good travel bro", Instant.parse("2019-06-20T18:53:07Z"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Enjoy it", Instant.parse("2019-06-20T19:53:07Z"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Have a good one", Instant.parse("2019-06-20T20:53:07Z"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(maria);

    }
}
