package com.elcom.jpa.sample.controller;

import com.elcom.jpa.sample.model.User;
import com.elcom.jpa.sample.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Cacheable("user")
    @GetMapping("user")
    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        log.info("LIST OF USERS", users);
        return users;
    }

    @PostMapping("/user")
    public User createEmployee(@RequestBody User user) {
        log.info("ADD 1 USER");
        return userRepository.save(user);
    }

    @CacheEvict(value = "user", allEntries=true)
    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id){

        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        userRepository.delete(user);

        log.info("DELETE USER WITH ID " + id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Cacheable(value = "user")
    @GetMapping("user/{id}")
    public Optional<User> findById(@PathVariable(value = "id") Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));

        log.info("USER WITH ID " + id );
        return Optional.ofNullable(user);
    }
}
