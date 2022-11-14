package com.example.UserManagement.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {
    @Id
    private Long personalNumber;
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    User() {
        // default constructor
    }
    public User(Long personalNumber, String name, Date dateOfBirth) {
        this.personalNumber = personalNumber;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Long personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
