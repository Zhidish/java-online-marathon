package com.softserve.itacademy;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.service.ToDoService;
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
import java.util.concurrent.atomic.AtomicBoolean;

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


    @Test
    public void updateToDO() {


        User user = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        List<ToDo> toDoList = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Message_1", Priority.HIGH));
        tasks.add(new Task("Message_2", Priority.LOW));
        tasks.add(new Task("Message_3", Priority.HIGH));
        ToDo todo = new ToDo("Todo_1", LocalDateTime.now(), user, tasks);
        toDoList.add(todo);
        user.setMyTodos(toDoList);
        toDoService.addTodo(todo, user);
        todo.setTitle("Todo_update");
        ToDo expected = toDoService.updateTodo(todo);
        Assertions.assertEquals(expected, todo);


    }


    @Test
    public void deleteToDo() {

        User user = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        List<ToDo> toDoList = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Message_1", Priority.HIGH));
        tasks.add(new Task("Message_2", Priority.LOW));
        tasks.add(new Task("Message_3", Priority.HIGH));
        ToDo todo = new ToDo("Todo_1", LocalDateTime.now(), user, tasks);
        toDoList.add(todo);
        user.setMyTodos(toDoList);
        toDoService.addTodo(todo, user);
        toDoService.deleteTodo(todo);
        AtomicBoolean boolean_actual = new AtomicBoolean(false);
        boolean expected = false;
        toDoService.getAll().forEach(toDoList_1 -> {
            if (toDoList_1.equals(todo)) {
                boolean_actual.set(true);
            }


        });

        Assertions.assertEquals(expected, boolean_actual.get());


    }

    @Test
    public void getAllTodo() {

        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);


        List<ToDo> expected = new ArrayList<>();
        User user = new User("Rostyslav", "Shynko", "longmail@gmail.com", "qwerty321", null);
        List<ToDo> toDoList = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Message_1", Priority.HIGH));
        tasks.add(new Task("Message_2", Priority.LOW));
        tasks.add(new Task("Message_3", Priority.HIGH));
        ToDo todo = new ToDo("Todo_1", LocalDateTime.now(), user, tasks);
        expected.add(todo);

        toDoList.add(todo);
        user.setMyTodos(toDoList);
        userService.addUser(user);


        User user1 = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        List<ToDo> toDoList1 = new ArrayList<>();
        List<Task> tasks1 = new ArrayList<>();
        tasks1.add(new Task("Message_1", Priority.HIGH));
        tasks1.add(new Task("Message_2", Priority.LOW));
        tasks1.add(new Task("Message_3", Priority.HIGH));
        ToDo todo1 = new ToDo("Todo_2", LocalDateTime.now(), user1, tasks1);
        toDoList1.add(todo1);
        expected.add(todo1);

        user1.setMyTodos(toDoList1);
        userService.addUser(user1);


        List<ToDo> actual = new ArrayList<>();


        userService.getAll().forEach(user2 -> actual.addAll(user2.getMyTodos()));


        Assertions.assertEquals(expected, actual);


    }


    @Test
    public void getByUser() {

        List<ToDo> expected = new ArrayList<>();
        List<ToDo> actual = new ArrayList<>();
        User user = new User("Rostyslav", "Shynko", "longmail@gmail.com", "qwerty321", null);
        List<ToDo> toDoList = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Message_1", Priority.HIGH));
        tasks.add(new Task("Message_2", Priority.LOW));
        tasks.add(new Task("Message_3", Priority.HIGH));
        ToDo todo = new ToDo("Todo_1", LocalDateTime.now(), user, tasks);
        expected.add(todo);

        toDoList.add(todo);
        user.setMyTodos(toDoList);
        userService.addUser(user);

        actual.addAll(toDoService.getByUser(user));

        Assertions.assertEquals(expected, actual);


        User user1 = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        List<ToDo> toDoList1 = new ArrayList<>();
        List<Task> tasks1 = new ArrayList<>();
        tasks1.add(new Task("Message_1", Priority.HIGH));
        tasks1.add(new Task("Message_2", Priority.LOW));
        tasks1.add(new Task("Message_3", Priority.HIGH));
        ToDo todo1 = new ToDo("Todo_2", LocalDateTime.now(), user1, tasks1);
        toDoList1.add(todo1);
        expected.add(todo1);


        user1.setMyTodos(toDoList1);
        userService.addUser(user1);
        actual.addAll(toDoService.getByUser(user1));

        Assertions.assertEquals(expected, actual);

    }


    @Test
    public void getByUserTitile() {


        User user = new User("Rostyslav", "Shynko", "longmail@gmail.com", "qwerty321", null);
        List<ToDo> toDoList = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Message_1", Priority.HIGH));
        tasks.add(new Task("Message_2", Priority.LOW));
        tasks.add(new Task("Message_3", Priority.HIGH));
        ToDo todo = new ToDo("Todo_1", LocalDateTime.now(), user, tasks);
        ToDo expected = todo;
        toDoList.add(todo);
        user.setMyTodos(toDoList);
        userService.addUser(user);
        ToDo actual = toDoService.getByUserTitle(user, "Todo_1");
        Assertions.assertEquals(actual, expected);

        User user1 = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        List<ToDo> toDoList1 = new ArrayList<>();
        List<Task> tasks1 = new ArrayList<>();
        tasks1.add(new Task("Message_1", Priority.HIGH));
        tasks1.add(new Task("Message_2", Priority.LOW));
        tasks1.add(new Task("Message_3", Priority.HIGH));
        ToDo todo1 = new ToDo("Todo_2", LocalDateTime.now(), user1, tasks1);
        expected = todo1;
        toDoList1.add(todo1);
        toDoList1.add(todo);// добавлення ще одного  листа  до листів
        user1.setMyTodos(toDoList1);// добавлення всіх листів ToDO

        userService.addUser(user1);

        actual = toDoService.getByUserTitle(user1, "Todo_2");

        Assertions.assertEquals(actual, expected);


    }
}