package com.softserve.itacademy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private List<User> users;

    public UserServiceImpl() {
        users = new ArrayList<>();
    }

    @Override
    public User addUser(User user) {
        System.out.println("incoming user id in addUser() " + user.getId());

        if (users.stream().noneMatch(user1 -> user1.equals(user))) {
            users.add(user);
        }
        //users.get(users.lastIndexOf(user))

        User user_1 = users.stream().filter(user1 -> user1.equals(user)).findFirst().get();
        System.out.println(" returning user id " + user_1.getId());

        return users.stream().filter(user1 -> user1.equals(user)).findFirst().get();
    }

    @Override
    public User updateUser(User user) {
        User user2 = null;
        System.out.println("incoming user id in updateUser() " + user.getId());
        users.stream()
                .filter(user1 -> user1.equals(user))
                .findFirst()
                .ifPresent(user1 -> user1 = user);


        user2 = users.stream().filter(user1 -> user1.equals(user)).findFirst().get();
        System.out.println("outcoming user id   " + user2.getId());
        return users.stream().filter(user1 -> user1.equals(user)).findFirst().get();


    }

    @Override
    public void deleteUser(User user) {

        users.remove(user);


    }

    @Override
    public List<User> getAll() {

        return users;
    }

}
