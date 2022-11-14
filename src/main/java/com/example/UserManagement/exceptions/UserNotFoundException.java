package com.example.UserManagement.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("user with id" +id+ " could not be found");
    }
}
