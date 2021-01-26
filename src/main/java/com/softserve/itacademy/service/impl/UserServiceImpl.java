package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
            return userRepository.save(user);
    }

    @Override
    public User readById(long id) {
        try {
            Optional<User> optional = userRepository.findById(id);
            return optional.get();


        }catch(NoSuchElementException e ){

            throw new EntityNotFoundException("no user found in DB ");
        }
    }

    @Override
    public User update(User user) {
            User oldUser = readById(user.getId());
                return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        try {
            User user = readById(id);
            userRepository.delete(user);
        }catch(NoSuchElementException e ){
            throw  new EntityNotFoundException(" on user found in DB");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

}
