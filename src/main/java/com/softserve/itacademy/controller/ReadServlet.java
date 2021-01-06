package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadServlet", value = "/read-task")
public class ReadServlet extends HttpServlet {

    TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/show-task.jsp");


        int id = Integer.parseInt(request.getParameter("id"));
        Task task = taskRepository.read(id);

        if (task == null) {
            request.setAttribute("id", id);
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);

        }

        request.setAttribute("task_1", task);
        requestDispatcher.forward(request, response);

    }
}
