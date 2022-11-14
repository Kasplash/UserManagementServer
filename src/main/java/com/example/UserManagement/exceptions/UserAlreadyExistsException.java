package com.example.UserManagement.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(Long personalNumber){
        super("user with personalnumber" +personalNumber+ " already exists");
    }
}
