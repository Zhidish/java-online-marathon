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
        users.add(user);
        return users.stream().filter(user1 -> user1.equals(user)).findFirst().get();
    }

    @Override
    public User updateUser(User user) {
        users.stream()
                .filter(user1 -> user1.getId() == user.getId())
                .findFirst()
                .ifPresent(user1 -> user1 = user);
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
