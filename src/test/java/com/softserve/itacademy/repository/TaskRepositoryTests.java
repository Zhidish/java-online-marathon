package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class TaskRepositoryTests {
    @Autowired
    TaskRepository taskRepository;

    @Test
    public void checkCreateTask() {
        Task task = new Task();
        task.setName("TaskCreate");
        task.setPriority(Priority.MEDIUM);
        long test_id = new Double(Math.random()*100).longValue();
        task.setId(test_id);
        task = taskRepository.save(task);
        assertEquals(test_id, task.getId());
    }
    @Test
    public void checkDeleteTaskByTask(){
        Task task = new Task();
        long test_id = new Double(Math.random()*100).longValue();
        task.setName("TaskDelete");
        task.setPriority(Priority.MEDIUM);
        task.setId(test_id);
        task = taskRepository.save(task);
        taskRepository.delete(task);
        assertFalse(taskRepository.existsById(task.getId()));
    }
    @Test
    public void checkDeleteTaskById(){
        Task task = new Task();
        long test_id = new Double(Math.random()*100).longValue();
        task.setName("TaskDeleteByID");
        task.setPriority(Priority.MEDIUM);
        task.setId(test_id);
        task = taskRepository.save(task);
        assertEquals(test_id, task.getId());
        taskRepository.deleteById(task.getId());
        assertFalse(taskRepository.existsById(task.getId()));
    }
    @Test
    public void checkDeleteAllTasks(){
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            task.setName("TaskDelete" + i);
            task.setPriority(Priority.MEDIUM);
            task.setId(test_id);
            taskRepository.save(task);
        }
        assertEquals(taskRepository.count(), 10);
        assertEquals(taskRepository.findAll().size(), 10);
        taskRepository.deleteAll();
        assertEquals(taskRepository.count(), 0);
        assertEquals(taskRepository.findAll().size(), 0);
    }
    @Test
    public void checkExistsTask(){
        Task task = new Task();
        long test_id = new Double(Math.random()*100).longValue();
        task.setName("TaskExists");
        task.setPriority(Priority.MEDIUM);
        task.setId(test_id);
        taskRepository.save(task);
        assertTrue(taskRepository.findAll().stream().anyMatch(task1 -> task1.getId() == task.getId()));
        assertTrue(taskRepository.existsById(task.getId()));
    }
    @Test
    public void checkFindTask(){
        Task task = new Task();
        long test_id = new Double(Math.random()*100).longValue();
        task.setName("TaskFind");
        task.setPriority(Priority.MEDIUM);
        task.setId(test_id);
        taskRepository.save(task);
        assertTrue(taskRepository.findAll().stream().anyMatch(task1 -> task1.getId() == task.getId()));
        assertEquals(task, taskRepository.findById(task.getId()).get());
    }
    @Test
    public void checkReturnAll(){
        List<Task> expectedTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            task.setName("Task" + i);
            task.setPriority(Priority.MEDIUM);
            task.setId(test_id);
            taskRepository.save(task);
            expectedTasks.add(task);
        }
        assertEquals(taskRepository.count(), 10);
        assertEquals(taskRepository.findAll().size(), 10);
        assertEquals(expectedTasks, taskRepository.findAll());
    }
    @Test
    public void checkCounter(){
        Set<Task> expectedTasks = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            task.setName("Task" + i);
            task.setPriority(Priority.MEDIUM);
            task.setId(test_id);
            taskRepository.save(task);
            expectedTasks.add(task);
        }
        assertEquals(taskRepository.count(), taskRepository.findAll().size(), expectedTasks.size());
        assertEquals(taskRepository.count(), expectedTasks.size());
        assertEquals(taskRepository.findAll().size(), expectedTasks.size());
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            task.setName("Task" + i*10);
            task.setPriority(Priority.MEDIUM);
            task.setId(test_id);
            taskRepository.save(task);
            expectedTasks.add(task);
        }
        assertEquals(taskRepository.count(), taskRepository.findAll().size(), expectedTasks.size());
        assertEquals(taskRepository.count(), expectedTasks.size());
        assertEquals(taskRepository.findAll().size(), expectedTasks.size());
    }
}
