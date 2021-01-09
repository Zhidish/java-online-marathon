package com.softserve.itacademy;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitPlatform.class)
public class TaskServiceTest {
    private static UserService userService;
    private static ToDoService toDoService;
    private static TaskService taskService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        taskService = annotationConfigContext.getBean(TaskService.class);


        annotationConfigContext.close();
    }

    @Test
    public void checkAddTask() throws NoSuchFieldException, IllegalAccessException {
        User user = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        userService.addUser(user);
        List<ToDo> toDoList = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Message_1", Priority.HIGH));
        tasks.add(new Task("Message_2", Priority.LOW));
        tasks.add(new Task("Message_3", Priority.HIGH));

        Task expected = new Task("Expected", Priority.MEDIUM);

        ToDo todo = new ToDo("Todo_1", LocalDateTime.now(), user, tasks);
        toDoList.add(todo);
        user.setMyTodos(toDoList);
       toDoService.addTodo(todo, user);

        Field namefiled = taskService.getClass().getDeclaredField("toDoService");

        namefiled.setAccessible(true);
        namefiled.set(taskService, toDoService);
        Task actual = taskService.addTask(expected, todo);

        Assertions.assertEquals(actual, expected);


    }

    @Test
    public void checkUpdateTask(){
        User user = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        userService.addUser(user);
        List<ToDo> toDoList = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        Task expected = new Task("Expected", Priority.HIGH);
        tasks.add(expected);
        ToDo todo = new ToDo("Todo_1", LocalDateTime.now(), user, tasks);
        toDoList.add(todo);
        user.setMyTodos(toDoList);
        expected.setName("Updated");
        Task actual = taskService.updateTask(expected);
        Assertions.assertEquals(expected, actual);


    }


    @Test
    public void checkDeleteTask() {
        User user = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);

        List<ToDo> toDoList = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        Task expected = new Task("Expected", Priority.HIGH);

        tasks.add(expected);
        tasks.add(new Task("Message_2", Priority.LOW));
        tasks.add(new Task("Message_3", Priority.HIGH));
        tasks.add(new Task("Message_4", Priority.LOW));
        tasks.add(new Task("Message_5", Priority.HIGH));


        ToDo todo = new ToDo("Todo_1", LocalDateTime.now(), user, tasks);
        toDoList.add(todo);
        user.setMyTodos(toDoList);
        userService.addUser(user);
        taskService.deleteTask(expected);

        Assertions.assertTrue(taskService.getAll().stream().noneMatch(task -> task.equals(expected)));
    }

    @Test
    public void checkGetAll() {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        taskService = annotationConfigContext.getBean(TaskService.class);

        List<Task> expected_tasks = new ArrayList<>();
        User user = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        List<ToDo> toDoList = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        Task expected = new Task("Expected", Priority.HIGH);

        tasks.add(expected);
        tasks.add(new Task("Message_2", Priority.LOW));
        tasks.add(new Task("Message_3", Priority.HIGH));

        expected_tasks.addAll(tasks);
        ToDo todo = new ToDo("Todo_1", LocalDateTime.now(), user, tasks);
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
        expected_tasks.addAll(tasks1);

        user1.setMyTodos(toDoList1);
        userService.addUser(user1);

        List<Task> actual_list = taskService.getAll();

        Assertions.assertEquals(expected_tasks, actual_list);

    }


    @Test
    public void checkGetByTodo() {
        List<Task> expected_tasks = new ArrayList<>();

        User user1 = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        List<ToDo> toDoList1 = new ArrayList<>();
        List<Task> tasks1 = new ArrayList<>();
        tasks1.add(new Task("Message_1", Priority.HIGH));
        tasks1.add(new Task("Message_2", Priority.LOW));
        tasks1.add(new Task("Message_3", Priority.HIGH));
        ToDo todo1 = new ToDo("Todo_2", LocalDateTime.now(), user1, tasks1);
        toDoList1.add(todo1);
        expected_tasks.addAll(tasks1);
        List<Task> actual_tasks = taskService.getByToDo(todo1);
        Assertions.assertEquals(expected_tasks, actual_tasks);
    }


    @Test
    public void checkGetByTodoName() {
        User user1 = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        List<ToDo> toDoList1 = new ArrayList<>();
        List<Task> tasks1 = new ArrayList<>();
        Task expected = new Task("Expected", Priority.LOW);
        tasks1.add(new Task("Message_1", Priority.HIGH));
        tasks1.add(new Task("Message_2", Priority.LOW));
        tasks1.add(new Task("Message_3", Priority.HIGH));
        tasks1.add(expected);
        ToDo todo1 = new ToDo("Todo_2", LocalDateTime.now(), user1, tasks1);
        toDoList1.add(todo1);
        Task actual = taskService.getByToDoName(todo1, "Expected");
        Assertions.assertEquals(expected, actual);

    }


    @Test
    public void checkGetByUserName() {
        User user1 = new User("Pavlo", "Juice", "email@gmail.com", "qwerty123", null);
        List<ToDo> toDoList1 = new ArrayList<>();
        List<Task> tasks1 = new ArrayList<>();
        Task expected = new Task("Expected", Priority.LOW);
        tasks1.add(new Task("Message_1", Priority.HIGH));
        tasks1.add(new Task("Message_2", Priority.LOW));
        tasks1.add(new Task("Message_3", Priority.HIGH));
        tasks1.add(expected);
        ToDo todo1 = new ToDo("Todo_2", LocalDateTime.now(), user1, tasks1);
        toDoList1.add(todo1);
        user1.setMyTodos(toDoList1);

        Task actual = taskService.getByUserName(user1, "Expected");
        Assertions.assertEquals(expected, actual);

    }


}
