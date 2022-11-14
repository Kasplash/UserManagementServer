package com.example.UserManagement.controller;

import com.example.UserManagement.exceptions.UserAlreadyExistsException;
import com.example.UserManagement.exceptions.UserNotFoundException;
import com.example.UserManagement.models.User;
import com.example.UserManagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        if(userRepository.existsById(newUser.getPersonalNumber())){
            throw new UserAlreadyExistsException(newUser.getPersonalNumber());
        }
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @DeleteMapping("/user/{personalnumber}")
    String deleteUser(@PathVariable Long personalnumber){
        if(!userRepository.existsById(personalnumber)) {
            throw new UserNotFoundException(personalnumber);
        }
        userRepository.deleteById(personalnumber);
        return "user " +personalnumber+ " has been deleted";
    }
}
