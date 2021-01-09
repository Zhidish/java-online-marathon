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
        Task result;
        System.out.println("adding " + task.toString());
        todo.getTasks().add(task);
        result = todo.getTasks().stream().filter(task1 -> task1.equals(task)).findFirst().get();
        System.out.println(task.toString() + " added");
        return result;
    }

    public Task updateTask(Task task) {
        Task result;
        System.out.println("updating " + task.toString());
        toDoService.getAll()
                .forEach(toDo -> toDo.getTasks()
                        .stream()
                        .filter(task1 -> task1.equals(task))
                        .forEach(task1 -> task1 = task));
        List<Task> tasks = new ArrayList<>();
        toDoService.getAll().forEach(toDo -> tasks.addAll(toDo.getTasks()));
        result = tasks.stream().filter(task1 -> task1.equals(task)).findAny().get();
        System.out.println(result.toString() + " updated");
        return result;

    }

    public void deleteTask(Task task) {
        System.out.println("deleting " + task.toString());
        toDoService.getAll()
                .forEach(toDo -> toDo.getTasks()
                        .stream()
                        .filter(task1 -> task1.equals(task)).findAny().ifPresent(task1 -> toDo.getTasks().remove(task1)));
        System.out.println(task.toString() + " deleted");
    }

    public List<Task> getAll() {
        System.out.println("getting all tasks");
        List<Task> result = new ArrayList<>();
        toDoService.getAll()
                .forEach(toDo -> result.addAll(toDo.getTasks()));
        return result;
    }

    public List<Task> getByToDo(ToDo todo) {
        System.out.println("getting all tasks of " + todo.toString());
        return todo.getTasks();
    }

    public Task getByToDoName(ToDo todo, String name) {
        Task result;
        System.out.println("getting task " + name + " in " + todo.toString());
        result = todo.getTasks().stream().filter(task -> task.getName().equals(name)).findFirst().get();
        System.out.println(result.toString() + "got");
        return result;
    }

    public Task getByUserName(User user, String name) {
        System.out.println("getting task " + name + " in " + user.toString());
        final Task[] result = new Task[1];
        user.getMyTodos().forEach(toDo -> result[0] = toDo.getTasks().stream().filter(task -> task.getName().equals(name)).findFirst().get());
        System.out.println(result[0].toString() + "got");
        return result[0];
    }

}
