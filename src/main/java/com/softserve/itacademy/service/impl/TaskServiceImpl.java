package com.softserve.itacademy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;

@Service
public class TaskServiceImpl implements TaskService {

    private ToDoService toDoService;

    @Autowired
    public TaskServiceImpl(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    public Task addTask(Task task, ToDo todo) {
        todo.getTasks().add(task);
        return todo.getTasks().stream().filter(task1 -> task1.equals(task)).findFirst().get();
    }

    public Task updateTask(Task task) {
        System.out.println(task);
        toDoService.getAll()
                .forEach(toDo -> toDo.getTasks()
                        .stream()
                        .filter(task1 -> task1.equals(task))
                        .forEach(task1 -> task1 = task));
        List<Task> tasks = new ArrayList<>();
        toDoService.getAll().forEach(toDo -> tasks.addAll(toDo.getTasks()));
        return tasks.stream().filter(task1 -> task1.equals(task)).findAny().get();


    }

    public void deleteTask(Task task) {
        toDoService.getAll()
                .forEach(toDo -> toDo.getTasks()
                        .stream()
                        .filter(task1 -> task1.equals(task))
                        .forEach(task1 -> toDo.getTasks().remove(task1)));
    }

    public List<Task> getAll() {
        List<Task> result = new ArrayList<>();
        toDoService.getAll()
                .forEach(toDo -> result.addAll(toDo.getTasks()));
        return result;
    }

    public List<Task> getByToDo(ToDo todo) {
        return todo.getTasks();
    }

    public Task getByToDoName(ToDo todo, String name) {
        return todo.getTasks().stream().filter(task -> task.getName().equals(name)).findFirst().get();
    }

    public Task getByUserName(User user, String name) {
        final Task[] result = new Task[1];
        user.getMyTodos().forEach(toDo -> result[0] = toDo.getTasks().stream().filter(task -> task.getName().equals(name)).findFirst().get());
        return result[0];
    }

}
