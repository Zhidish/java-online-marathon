package com.softserve.itacademy;

import com.softserve.itacademy.model.ToDo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitPlatform.class)
public class UserServiceTest {
    private static UserService userService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        annotationConfigContext.close();
    }

    @Test
    public void checkAddUser() {
        User user = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        User actual = userService.addUser(user);
        Assertions.assertEquals(user, actual, "User isn't added");
    }

    @Test
    public void checkUpdateUser(){
        User user = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        userService.addUser(user);
        user.setFirstName("Andrew");
        user.setLastName("Gorbachew");
        user.setEmail("anotheremail@gmail.com");
        user.setPassword("123");
        User actual = userService.updateUser(user);
        Assertions.assertEquals(user, actual, "User isn't updated");
    }

    @Test
    public void checkDeleteUser(){
        User user = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        userService.addUser(user);
        userService.deleteUser(user);
        Assertions.assertFalse(userService.getAll().contains(user), "User isn't deleted");
    }

    @Test
    public void checkAllUsersExist(){
        User user1 = new User("Pavlo", "Juice", "email1@gmail.com", "12345", null);
        User user2 = new User("Alina", "Stepanova", "email2@gmail.com", "54321", null);
        User user3 = new User("Stepan", "Pavlov", "email3@gmail.com", "13579", null);
        User[] expectedUsers = {user1, user2, user3};
        userService.addUser(user1);
        userService.addUser(user1);
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        Assertions.assertArrayEquals(expectedUsers, userService.getAll().toArray(), "User list is wrong");
    }

}
