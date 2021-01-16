package com.softserve.itacademy.service;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateUser(){
        User user = new User();
        user.setName("Createuser");
        user = userService.create(user);
        assertTrue(userRepository.existsById(user.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReadUser(){
        User user = new User();
        user.setName("Readuser");
        user = userService.create(user);
        assertEquals(user, userService.readById(user.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkUpdateUser(){
        User user1 = new User();
        User user2;
        user1.setName("Updateeuser");
        user1 = userService.create(user1);
        user1.setName("Updateeuser1");
        user2 = userService.update(user1);
        assertEquals(user1, user2);
        assertEquals(user1.getName(), user2.getName());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteUser(){
        User user = new User();
        user.setName("Readuser");
        user = userService.create(user);
        userService.delete(user.getId());
        assertNull(userService.readById(user.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkGetAllUsers(){
        List<User> expectedUsers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("Readuser"+i);
            user = userService.create(user);
            expectedUsers.add(user);
        }
        assertEquals(expectedUsers.size(), userService.getAll().size());

    }
}