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
        ToDo result;
        System.out.println("adding " + todo.toString());
        user.getMyTodos().add(todo);
        result = user.getMyTodos().stream().filter(toDo -> toDo.equals(todo)).findFirst().get();
        System.out.println(todo.toString() + " added");
        return result;


    }

    public ToDo updateTodo(ToDo todo) {
        ToDo todo_result;
        System.out.println("updating " + todo.toString());
        userService.getAll().forEach(user -> user.getMyTodos()
                .stream().filter(toDo -> toDo.equals(todo)).forEach(toDo -> toDo = todo)
        );
        List<ToDo> result = new ArrayList<>();
        userService.getAll().forEach(user -> result.addAll(user.getMyTodos()));
        todo_result= result.stream().filter(toDo -> toDo.equals(todo)).findFirst().get();
        System.out.println(todo.toString() + " updated");
        return todo_result;
    }

    public void deleteTodo(ToDo todo) {
        System.out.println("deleting " + todo.toString());
        userService.getAll().stream().filter(user -> user.getMyTodos().contains(todo)).forEach(s -> s.getMyTodos().remove(todo));
        System.out.println(todo.toString() + " deleted");
    }

    public List<ToDo> getAll() {
        System.out.println("getting all todo`s");
        List<ToDo> todoList = new ArrayList<>();
        userService.getAll().forEach(s -> todoList.addAll(s.getMyTodos()));
        return todoList;
    }

    public List<ToDo> getByUser(User user) {
        System.out.println("getting todo`s by user");
        return user.getMyTodos();
    }

    public ToDo getByUserTitle(User user, String title) {
        ToDo result;
        System.out.println("getting user todo`s by title");
        result = user.getMyTodos().stream().filter(ToDo -> ToDo.getTitle().equals(title)).findFirst().get();
        System.out.println("result todo " + result.toString());
        return user.getMyTodos().stream().filter(ToDo -> ToDo.getTitle().equals(title)).findFirst().get();


    }

}
