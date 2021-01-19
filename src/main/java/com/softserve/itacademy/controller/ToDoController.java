package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.impl.ToDoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/todos")
public class ToDoController {


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
    ToDoServiceImpl toDoService;


    @GetMapping("/{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("todo", toDoService.readById(id));
        return "update-todo";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        toDoService.delete(id);
        return "redirect:/todos/all";
    }

    @PostMapping("/update")
    public String updatingToDo(@ModelAttribute(name="todo") ToDo toDo) {
        toDoService.update(toDo);
        return "redirect:/todos/all";
    }

    @GetMapping("/all/{id}")
    public String todosPage(Model model) {

        model.addAttribute("userName","some user name //need to be done");
        model.addAttribute("todos", toDoService.getAll());
        model.addAttribute("counter", new Counter());
        return "todo-lists";
    }

}
