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
        ToDo toDo1 = null;
        System.out.println("incoming todo id  in addTodo() " + todo.getId());
        user.getMyTodos().add(todo);


        toDo1 = user.getMyTodos().stream().filter(toDo -> toDo.equals(todo)).findFirst().get();
        System.out.println("outcoming todo id in addToDo " +toDo1.getId());
        return user.getMyTodos().stream().filter(toDo -> toDo.equals(todo)).findFirst().get();


    }

    public ToDo updateTodo(ToDo todo) {
        ToDo todo_1=null;
        System.out.println("incoming todo id  in updateToDo() "+ todo.getId());
        userService.getAll().forEach(user -> user.getMyTodos()
                .stream().filter(toDo -> toDo.equals(todo)).forEach(toDo -> toDo = todo)
        );
        List<ToDo> result = new ArrayList<>();
        userService.getAll().forEach(user -> result.addAll(user.getMyTodos()));

               todo_1= result.stream().filter(toDo -> toDo.equals(todo)).findFirst().get();
        System.out.println("outcoming todo id "+ todo_1.getId());
        return result.stream().filter(toDo -> toDo.equals(todo)).findFirst().get();
    }

    public void deleteTodo(ToDo todo) {
        userService.getAll().stream().filter(user -> user.getMyTodos().contains(todo)).forEach(s -> s.getMyTodos().remove(todo));
    }

    public List<ToDo> getAll() {

        List<ToDo> todoList = new ArrayList<>();
        userService.getAll().forEach(s -> todoList.addAll(s.getMyTodos()));
        return todoList;
    }

    public List<ToDo> getByUser(User user) {
        return user.getMyTodos();
    }

    public ToDo getByUserTitle(User user, String title) {
        ToDo todo1 = null ;
        System.out.println("need to find ToDo with such title "+ title );
        todo1= user.getMyTodos().stream().filter(ToDo -> ToDo.getTitle().equals(title)).findFirst().get();
        System.out.println("outcoming todo with title "+ todo1.getTitle() );
        return user.getMyTodos().stream().filter(ToDo -> ToDo.getTitle().equals(title)).findFirst().get();


    }

}
