package com.elcom.sample1.service.impl;

import com.elcom.sample1.model.User;
import com.elcom.sample1.repository.UserRepository;
import com.elcom.sample1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable("user")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Cacheable("user")
    public Optional<User> findById(Long id) {

        return userRepository.findById(id);
    }

    public void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @CacheEvict(value = "user", allEntries = true)
    public void clearCache() {
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }
}
