package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Test
    public void checkCreateUser() {
        User user = new User();
        user.setEmail("valid2@cv.edu.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        long test_id = new Double(Math.random()*100).longValue();
        user.setId(test_id);
        user = userRepository.save(user);
        assertEquals(test_id, user.getId());
    }
    @Test
    public void checkDeleteUserByUser(){
        User user = new User();
        long test_id = new Double(Math.random()*100).longValue();
        user.setEmail("valid2@cv.edu.ua");
        user.setFirstName("Delete");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        user.setId(test_id);
        user = userRepository.save(user);
        userRepository.delete(user);
        assertFalse(userRepository.existsById(user.getId()));
    }
    @Test
    public void checkDeleteUserById(){
        User user = new User();
        long test_id = new Double(Math.random()*100).longValue();
        user.setEmail("valid2@cv.edu.ua");
        user.setFirstName("Dellete");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        user.setId(test_id);
        user = userRepository.save(user);
        assertEquals(test_id, user.getId());
        userRepository.deleteById(user.getId());
        assertFalse(userRepository.existsById(user.getId()));
    }
    @Test
    public void checkDeleteAllUsers(){
        for (int i = 0; i < 10; i++) {
            User user = new User();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            user.setEmail("valid2@cv.edu.ua");
            user.setFirstName("Deletell");
            user.setLastName("Users");
            user.setPassword("qwQW12!@");
            user.setId(test_id);
            userRepository.save(user);
        }
        assertEquals(userRepository.count(), 10);
        assertEquals(userRepository.findAll().size(), 10);
        userRepository.deleteAll();
        assertEquals(userRepository.count(), 0);
        assertEquals(userRepository.findAll().size(), 0);
    }
    @Test
    public void checkExistsUser(){
        User user = new User();
        long test_id = new Double(Math.random()*100).longValue();
        user.setEmail("valid2@cv.edu.ua");
        user.setFirstName("Existed");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        user.setId(test_id);
        userRepository.save(user);
        assertTrue(userRepository.findAll().stream().anyMatch(user1 -> user1.getId() == user.getId()));
        assertTrue(userRepository.existsById(user.getId()));
    }
    @Test
    public void checkFindUser(){
        User user = new User();
        long test_id = new Double(Math.random()*100).longValue();
        user.setEmail("valid2@cv.edu.ua");
        user.setFirstName("Found");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        user.setId(test_id);
        userRepository.save(user);
        assertTrue(userRepository.findAll().stream().anyMatch(user1 -> user1.getId() == user.getId()));
        assertEquals(user, userRepository.findById(user.getId()).get());
    }
    @Test
    public void checkReturnAll(){
        List<User> expectedUsers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            user.setEmail("valid2@cv.edu.ua");
            user.setFirstName("Returned");
            user.setLastName("User");
            user.setPassword("qwQW12!@");
            user.setId(test_id);
            userRepository.save(user);
            expectedUsers.add(user);
        }
        assertEquals(userRepository.count(), 10);
        assertEquals(userRepository.findAll().size(), 10);
        assertEquals(expectedUsers, userRepository.findAll());
    }
    @Test
    public void checkCounter(){
        Set<User> expectedUsers = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            user.setEmail("valid2@cv.edu.ua");
            user.setFirstName("Counter");
            user.setLastName("User");
            user.setPassword("qwQW12!@");
            user.setId(test_id);
            userRepository.save(user);
            expectedUsers.add(user);
        }
        assertEquals(userRepository.count(), userRepository.findAll().size(), expectedUsers.size());
        assertEquals(userRepository.count(), expectedUsers.size());
        assertEquals(userRepository.findAll().size(), expectedUsers.size());
        for (int i = 0; i < 10; i++) {
            User user = new User();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            user.setEmail("valid2@cv.edu.ua");
            user.setFirstName("Counter");
            user.setLastName("User");
            user.setPassword("qwQW12!@");
            user.setId(test_id);
            userRepository.save(user);
            expectedUsers.add(user);
        }
        assertEquals(userRepository.count(), userRepository.findAll().size(), expectedUsers.size());
        assertEquals(userRepository.count(), expectedUsers.size());
        assertEquals(userRepository.findAll().size(), expectedUsers.size());
    }
}
