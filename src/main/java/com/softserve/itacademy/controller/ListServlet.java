package com.softserve.itacademy.controller;

import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TaskList",value="/tasks-list")
public class ListServlet extends HttpServlet {
    TaskRepository taskRepository;
    @Override
    public void init(){
        taskRepository=TaskRepository.getTaskRepository();


    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("tasks",taskRepository.all());

        request.getRequestDispatcher("/WEB-INF/pages/show-tasks.jsp").forward(request, response);



    }
}
