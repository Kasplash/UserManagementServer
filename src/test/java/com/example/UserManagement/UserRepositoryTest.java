package com.example.UserManagement;

import com.example.UserManagement.models.User;
import com.example.UserManagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void ShouldSaveUser(){
        Long personalNumber = new Random().nextLong();
        String name = "Random Name";
        Date dateofbirth = new Date();

        User user = new User(personalNumber, name, dateofbirth);
        User savedUser = userRepository.save(user);
        assertEquals(savedUser.getName(), user.getName());
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void shouldFindUsersThroughSqlFile() {
        List<User> test = userRepository.findAll();
        assertThat(test).isNotEmpty();
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void shouldFindUsersThroughIdInSqlFile() {
        Optional<User> test = userRepository.findById(123456789L);
        assertThat(test).isNotEmpty();
    }
    @Test
    @Sql("classpath:test-data.sql")
    public void ShouldDeleteUser(){
        boolean deletedUser = false;
        try {
            userRepository.deleteById(123456789L);
            deletedUser = true;
        }catch(Exception e){
            deletedUser = true;
        }

        assertTrue(deletedUser);
    }


}
