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
        System.out.println("adding " + user.toString());
        if (users.stream().noneMatch(user1 -> user1.equals(user))) {
            users.add(user);
            System.out.println(user.toString() + " added");
        }
        //users.get(users.lastIndexOf(user))
        return users.stream().filter(user1 -> user1.equals(user)).findFirst().get();
    }

    @Override
    public User updateUser(User user) {
        User result;
        System.out.println("updating " + user.toString());
        users.stream()
                .filter(user1 -> user1.equals(user))
                .findFirst()
                .ifPresent(user1 -> user1 = user);
        result = users.stream().filter(user1 -> user1.equals(user)).findFirst().get();
        System.out.println(user.toString() + " updated");
        return result;
    }

    @Override
    public void deleteUser(User user) {
        System.out.println("deleting " + user.toString());
        users.remove(user);
        System.out.println(user.toString() + " deleted");
    }

    @Override
    public List<User> getAll() {
        System.out.println("getting all users");
        return users;
    }

}
