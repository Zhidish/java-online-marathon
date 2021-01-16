package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.repository.impl.ToDoRepositoryImpl;
import com.softserve.itacademy.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {
    ToDoRepositoryImpl toDoRepository =  new ToDoRepositoryImpl();

    @Override
    public ToDo create(ToDo todo) {
        if (toDoRepository.existsById(todo.getId())){
            update(todo);
        }
        else {
            toDoRepository.save(todo);
        }
        return toDoRepository.findById(todo.getId()).get();
    }

    @Override
    public ToDo readById(long id) {
        if (toDoRepository.existsById(id)){
            return toDoRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public ToDo update(ToDo todo) {
        if (toDoRepository.existsById(todo.getId())) {
            return toDoRepository.save(todo);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        if (toDoRepository.existsById(id)){
            toDoRepository.deleteById(id);
        }
    }

    @Override
    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        User user = new User();
        user.setId(userId);
        return toDoRepository.getAllByUser(user);
    }
}
