package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.repository.impl.UserRepositoryImpl;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepositoryImpl userRepository = new UserRepositoryImpl();

    @Override
    public User create(User user) {
        if (userRepository.existsById(user.getId())){
            update(user);
        }else {
            userRepository.save(user);
        }
        return userRepository.findById(user.getId()).get();
    }

    @Override
    public User readById(long id) {
        if (userRepository.existsById(id)){
            return userRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
