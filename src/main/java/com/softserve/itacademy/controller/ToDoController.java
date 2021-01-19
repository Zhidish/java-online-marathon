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

    @GetMapping("/all")
    public String todosPage(Model model) {
        int counter = 0;
        model.addAttribute("userName","some user name //need to be done");
        model.addAttribute("todos", toDoService.getAll());
        model.addAttribute("counter", new Counter());
        return "todo-lists";
    }

    //add needed fields

//    @GetMapping("/create/users/{owner_id}")
//    public String create(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @PostMapping("/create/users/{owner_id}")
//    public String create(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{id}/tasks")
//    public String read(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{todo_id}/update/users/{owner_id}")
//    public String update(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @PostMapping("/{todo_id}/update/users/{owner_id}")
//    public String update(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{todo_id}/delete/users/{owner_id}")
//    public String delete(//add needed parameters) {
//                         // ToDo
//        return " ";
//    }
//
//    @GetMapping("/all/users/{user_id}")
//    public String getAll(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{id}/add")
//    public String addCollaborator(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
//    @GetMapping("/{id}/remove")
//    public String removeCollaborator(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
}
