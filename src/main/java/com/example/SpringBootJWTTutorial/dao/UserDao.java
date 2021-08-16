package com.example.SpringBootJWTTutorial.dao;

import com.example.SpringBootJWTTutorial.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
}
