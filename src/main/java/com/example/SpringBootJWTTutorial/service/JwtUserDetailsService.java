package com.example.SpringBootJWTTutorial.service;

import com.example.SpringBootJWTTutorial.dao.UserDao;
import com.example.SpringBootJWTTutorial.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username " + username);
        }
    }

    public com.example.SpringBootJWTTutorial.model.User saveUser(UserDTO userDTO) {
        com.example.SpringBootJWTTutorial.model.User user = new com.example.SpringBootJWTTutorial.model.User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        return userDao.save(user);
    }
}