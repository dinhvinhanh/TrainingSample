package com.elcom.jpa.sample.service.impl;

import com.elcom.jpa.sample.model.User;
import com.elcom.jpa.sample.repository.UserRepository;
import com.elcom.jpa.sample.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable("user")
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        log.info("LIST OF USERS:", users);
        return users;
    }

    @Override
    @Cacheable(value = "user")
    public Optional<User> findById(Long id) {
        log.info("USER WITH ID " + id );
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        log.info("ADD 1 USER ");
        return userRepository.save(user);
    }

    @CacheEvict(value = "user", allEntries = true)
    public void clearCache() {
    }

    @CacheEvict(value = "user", allEntries=true)
    public void removeUser(Long id){
        userRepository.deleteById(id);
        log.info("DELETE USER WITH ID " + id);
    }

}
