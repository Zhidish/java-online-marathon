package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "EditTask", value = "/edit-task")
public class EditTaskServlet extends HttpServlet {
    TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("task");
        Priority priority = Priority.valueOf(request.getParameter("priority"));

        Task task  = taskRepository.read(id);
        task.setTitle(title);
        task.setPriority(priority);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/show-tasks.jsp");

        request.setAttribute("tasks",taskRepository.all());
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Task task = taskRepository.read(id);

        if (task == null) {
            request.setAttribute("id", id);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        else{
            request.setAttribute("task", task);
            request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
        }

    }

}
