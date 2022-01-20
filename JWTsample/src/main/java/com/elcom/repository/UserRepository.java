package com.elcom.repository;
import com.elcom.model.UserDAO;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<UserDAO, Integer> {
    UserDAO findByUsername(String username);
}