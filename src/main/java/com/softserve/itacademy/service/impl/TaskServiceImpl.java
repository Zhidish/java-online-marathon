package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.service.TaskService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        try {
            return taskRepository.save(task);
        } catch (IllegalArgumentException e) {
            throw new NullEntityReferenceException("Attempt to create an empty Task");
        }
    }

    @Override
    public Task readById(long id) {
        try {
            Optional<Task> optional = taskRepository.findById(id);

            return optional.get();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("no task found  in DB");


        }
    }

    @Override
    public Task update(Task task) {
        Task oldTask = readById(task.getId());
        try {
            return taskRepository.save(task);
        } catch (IllegalArgumentException e) {
            throw new NullEntityReferenceException("Attempt to update an empty Task");
        }
    }

    @Override
    public void delete(long id) {
        try {
            Task task = readById(id);
            taskRepository.delete(task);
        }catch(NoSuchElementException e ){
            throw new EntityNotFoundException("no task found in DB ");

        }
    }

    @Override
    public List<Task> getAll() {
        try {
            List<Task> tasks = taskRepository.findAll();
            return tasks.isEmpty() ? new ArrayList<>() : tasks;
        }catch(NoSuchElementException e ){
            throw new EntityNotFoundException("no tasks foud ");

        }
    }

    @Override
    public List<Task> getByTodoId(long todoId) {
        try {
            List<Task> tasks = taskRepository.getByTodoId(todoId);
            return tasks.isEmpty() ? new ArrayList<>() : tasks;
        }catch (NoSuchElementException e ){

            throw new EntityNotFoundException("no todo found in DB");
        }

    }
}
