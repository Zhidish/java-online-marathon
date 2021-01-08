package com.softserve.itacademy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;

@Service
public class ToDoServiceImpl implements ToDoService {

    private UserService userService;

    @Autowired
    public ToDoServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public ToDo addTodo(ToDo todo, User user) {
        user.getMyTodos().add(todo);
        return user.getMyTodos().stream().filter(toDo -> toDo.equals(todo)).findFirst().get();
    }

    public ToDo updateTodo(ToDo todo) {
        userService.getAll().forEach(user -> user.getMyTodos()
                .stream().filter(toDo -> toDo.equals(todo)).forEach(toDo -> toDo = todo)
        );
        List<ToDo> result = new ArrayList<>();
        userService.getAll().forEach(user -> result.addAll(user.getMyTodos()));
        return result.stream().filter(toDo -> toDo.equals(todo)).findFirst().get();
    }

    public void deleteTodo(ToDo todo) {
        userService.getAll().stream().filter(user -> user.getMyTodos().contains(todo)).forEach(s -> s.getMyTodos().remove(todo));
    }

    public List<ToDo> getAll() {
        List<ToDo> todoList = new ArrayList<>();
        userService.getAll().forEach(s-> todoList.addAll(s.getMyTodos()));
        return todoList;
    }

    public List<ToDo> getByUser(User user) {
        return user.getMyTodos() ;
    }

    public ToDo getByUserTitle(User user, String title) {
      return user.getMyTodos().stream().filter(ToDo->ToDo.getTitle().equals(title)).findFirst().get();
    }

}
