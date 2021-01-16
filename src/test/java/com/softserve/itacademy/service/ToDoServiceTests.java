package com.softserve.itacademy.service;

import com.softserve.itacademy.model.ToDo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class ToDoServiceTests {

    ToDoService toDoService;

    @Autowired
    public ToDoServiceTests(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @Test
    @Transactional
    @DirtiesContext
    public void getAllToDosByUserIdTest(){
        int expectedSize = 2;
        List<ToDo> toDos = toDoService.getByUserId(5L);
        assertEquals(expectedSize, toDos.size());
    }

}
