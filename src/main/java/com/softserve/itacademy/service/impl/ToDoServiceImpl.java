package com.softserve.itacademy.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sun.tools.javac.comp.Todo;
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
        return todo;
    }

    public ToDo updateTodo(ToDo todo) {
        userService.getAll().forEach(s -> s.getMyTodos()
                .stream().filter(h -> h.gitId() == todo.gitId()).forEach(todod -> todod = todo)

        );

        return todo;
    }

    public void deleteTodo(ToDo todo) {
        userService.getAll().forEach(s -> s.getMyTodos().remove(todo));
    }

    public List<ToDo> getAll() {
        List<ToDo> todoList=new ArrayList<>();
        userService.getAll().forEach(s->s.getMyTodos().forEach(h->todoList.add(h)));
        return todoList;
    }

    public List<ToDo> getByUser(User user) {
        return user.getMyTodos() ;
    }

    public ToDo getByUserTitle(User user, String title) {
      return   user.getMyTodos().stream().filter(ToDo->ToDo.getTitle().equals(title)).findFirst().get();

    }

}
