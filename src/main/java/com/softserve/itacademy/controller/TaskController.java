package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.impl.StateServiceImpl;
import com.softserve.itacademy.service.impl.TaskServiceImpl;
import com.softserve.itacademy.service.impl.ToDoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {


    class Counter {
        private int counter = 0;

        Counter() {

        }

        private void count() {
            counter++;


        }

        public int getCounter() {
            this.count();
            return counter;
        }

    }

    @Autowired
    StateServiceImpl stateService;

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    ToDoServiceImpl toDoService;

    @GetMapping("/all/{user_id}/{todo_id}")
    public String tasksPage(@PathVariable(name = "user_id") Integer id,
                            @PathVariable(name = "todo_id") Integer todo_id,
                            Model model) {
        List<Task> tasks = new ArrayList<>();



        toDoService.getByUserId(id).forEach(toDo -> tasks.addAll(toDo.getTasks()));

        tasks.forEach(task -> {
            System.err.println(task.toString());

        });
        model.addAttribute("tasks", tasks);
        model.addAttribute("counter", new Counter());
        model.addAttribute("todo_id",todo_id);
        model.addAttribute("todo_name",toDoService.readById(todo_id).getTitle());

        return "todo-tasks";
    }


    @PostMapping("/create/todos/{todo_id}")
    public String create(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "priority") String priority,
            @RequestParam(name = "status") String status,
            @PathVariable(name = "todo_id") Integer id,
            Model model) {

        System.err.println(name);
        System.err.println(priority);
        System.err.println(status);
        System.err.println(id);

        Task task = new Task();
        task.setName(name);
        task.setState(stateService.getByName(status));
        task.setTodo(toDoService.readById(id));
        task.setPriority(Priority.valueOf(priority));

        taskService.create(task);

        return "redirect:/tasks/all/" + toDoService.readById(id).getOwner().getId() +"/" +id;

    }

    @GetMapping("/create/todos/{todo_id}")
    public String create_(@PathVariable(name = "todo_id") Integer id, Model model) {
        model.addAttribute("todo_id", id);
        return "create-task";
    }

    @GetMapping("/{task_id}/update/todos/{todo_id}")
    public String update(@PathVariable(name = "task_id") Integer task_id,
                         @PathVariable(name = "todo_id") Integer todo_id,
                         Model model
    ) {

        model.addAttribute("task", taskService.readById(task_id));


        return "update-task";
    }

    @PostMapping("/{task_id}/update/todos/{todo_id}")
    public String update_(@RequestParam(name = "name") String name,
                          @RequestParam(name = "priority") String priority,
                          @RequestParam(name = "status") String status,
                          @PathVariable(name = "task_id") Integer id,
                          @PathVariable(name = "todo_id") Integer todo_id

    ) {
        System.err.println(name);
        System.err.println(priority);
        Task task = taskService.readById(id);
        task.setName(name);
        task.setPriority(Priority.valueOf(priority));
        task.setState(stateService.getByName(status));
        taskService.update(task);

        return "redirect:/tasks/all/" + toDoService.readById(todo_id).getOwner().getId()+"/"+todo_id;
    }

    @GetMapping("/{task_id}/delete/todos/{todo_id}")
    public String delete(@PathVariable(name = "task_id") Integer task_id,
                         @PathVariable(name = "todo_id") Integer todo_id) {
        taskService.delete(task_id);

        return "redirect:/tasks/all/" + toDoService.readById(todo_id).getOwner().getId()+"/"+todo_id;
    }


}
