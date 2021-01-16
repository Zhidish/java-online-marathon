package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.repository.impl.TaskRepositoryImpl;
import com.softserve.itacademy.repository.impl.ToDoRepositoryImpl;
import com.softserve.itacademy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepositoryImpl taskRepository = new TaskRepositoryImpl();

    @Override
    public Task create(Task task) {
        if (taskRepository.existsById(task.getId())){
            update(task);
        }
        else {
            taskRepository.save(task);
        }
        return taskRepository.findById(task.getId()).get();
    }

    @Override
    public Task readById(long id) {
        if (taskRepository.existsById(id)){
            return taskRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Task update(Task task) {
        if (taskRepository.existsById(task.getId())) {
            return taskRepository.save(task);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        if (taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getByTodoId(long todoId) {
        ToDo toDo = new ToDo();
        toDo.setId(todoId);
        return taskRepository.getTasksByToDoId(toDo.getId());
    }
}
