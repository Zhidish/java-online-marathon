package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.service.ToDoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository todoRepository;

    public ToDoServiceImpl(ToDoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public ToDo create(ToDo todo) {
        try {
            return todoRepository.save(todo);
        } catch (IllegalArgumentException e) {
            throw new NullEntityReferenceException("Attempt to create an empty ToDo");
        }
    }

    @Override
    public ToDo readById(long id) {
        try {
            Optional<ToDo> optional = todoRepository.findById(id);
            return optional.get();
        }catch(NoSuchElementException e ){
            throw new EntityNotFoundException("no todo found in DB");

        }
    }

    @Override
    public ToDo update(ToDo todo) {
            ToDo oldTodo = readById(todo.getId());
        try {
            return todoRepository.save(todo);
        } catch (IllegalArgumentException e) {
            throw new NullEntityReferenceException("Attempt to update an empty ToDo");
        }
    }

    @Override
    public void delete(long id) {
        try {
            ToDo todo = readById(id);
            todoRepository.delete(todo);
        }catch(NoSuchElementException e ) {
            throw new EntityNotFoundException("no todo found in DB");
        }
    }

    @Override
    public List<ToDo> getAll() {
        try {
            List<ToDo> todos = todoRepository.findAll();
            return todos.isEmpty() ? new ArrayList<>() : todos;
        }catch(NoSuchElementException e ){
            throw new EntityNotFoundException("no todoes");

        }
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        try {
            List<ToDo> todos = todoRepository.getByUserId(userId);
            return todos.isEmpty() ? new ArrayList<>() : todos;
        }catch(NoSuchElementException e ){
            throw new EntityNotFoundException("no user found ");
        }
    }
}
