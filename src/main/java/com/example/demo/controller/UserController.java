package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDAO;
import com.example.demo.exception.UserNotFoundException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/users")
    public List<User> retrieveAllUsers()
    {
        return userDAO.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) throws Exception {
        User user1=userDAO.findOne(id);
        if(user1==null)
        {
            throw new NotFoundException("Kayit bulunamadi...");
        }
        return user1;

    }


    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user)
    {
        User savedUser=userDAO.save(user);


        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
