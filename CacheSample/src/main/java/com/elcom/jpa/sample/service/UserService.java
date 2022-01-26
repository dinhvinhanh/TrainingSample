package com.elcom.jpa.sample.service;

import com.elcom.jpa.sample.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService{

    List<User> findAll();

    Optional<User> findById(Long id);

    User saveUser(User user);

    void removeUser (Long id);

    public void clearCache();
}
