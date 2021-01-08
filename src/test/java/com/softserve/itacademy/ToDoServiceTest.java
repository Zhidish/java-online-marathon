package com.softserve.itacademy;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.impl.ToDoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitPlatform.class)
public class ToDoServiceTest {
    private static UserService userService;
    private static ToDoService toDoService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        annotationConfigContext.close();
    }

    @Test
    public void checkAddToDo() {
        User user = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        List<ToDo> toDoList = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Message_1", Priority.HIGH));
        tasks.add(new Task("Message_2", Priority.LOW));
        tasks.add(new Task("Message_3", Priority.HIGH));
        ToDo todo = new ToDo("Todo_1", LocalDateTime.now(), user, tasks);
        toDoList.add(todo);
        user.setMyTodos(toDoList);
        ToDo expected = toDoService.addTodo(todo, user);
        Assertions.assertEquals(todo, expected);
    }

    // TODO, other tests
}
