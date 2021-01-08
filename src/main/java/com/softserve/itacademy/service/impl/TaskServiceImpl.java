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
        Task task_1 = null;
        System.out.println("incoming  task id in addTask() " + task.getId());
        todo.getTasks().add(task);

        task_1 = todo.getTasks().stream().filter(task1 -> task1.equals(task)).findFirst().get();
        System.out.println("outcoming task id " + task_1.getId());
        return todo.getTasks().stream().filter(task1 -> task1.equals(task)).findFirst().get();
    }

    public Task updateTask(Task task) {
        Task updatingTask = null;
        System.out.println("incoming task id in updatetask() " + task.getId());

        toDoService.getAll()
                .forEach(toDo -> toDo.getTasks()
                        .stream()
                        .filter(task1 -> task1.equals(task))
                        .forEach(task1 -> task1 = task));
        List<Task> tasks = new ArrayList<>();
        toDoService.getAll().forEach(toDo -> tasks.addAll(toDo.getTasks()));
        updatingTask = tasks.stream().filter(task1 -> task1.equals(task)).findAny().get();
        System.out.println("outcoming task id " + updatingTask.getId());

        return tasks.stream().filter(task1 -> task1.equals(task)).findAny().get();

    }

    public void deleteTask(Task task) {
        toDoService.getAll()
                .forEach(toDo -> toDo.getTasks()
                        .stream()
                        .filter(task1 -> task1.equals(task)).findAny().ifPresent(task1 -> toDo.getTasks().remove(task1)));
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
        Task task_1 = null;
        System.out.println("incoming name of task in getByDoName() " + name);


        task_1 = todo.getTasks().stream().filter(task -> task.getName().equals(name)).findFirst().get();
        System.out.println("outcoming task name " + task_1.getName());
        return todo.getTasks().stream().filter(task -> task.getName().equals(name)).findFirst().get();
    }

    public Task getByUserName(User user, String name) {
        Task task_1 = null;

        System.out.println("incoming name of task in getByUserName " + name);
        final Task[] result = new Task[1];
        user.getMyTodos().forEach(toDo -> result[0] = toDo.getTasks().stream().filter(task -> task.getName().equals(name)).findFirst().get());
        System.out.println("outcoming name  of task " + result[0].getName());
        return result[0];
    }

}
