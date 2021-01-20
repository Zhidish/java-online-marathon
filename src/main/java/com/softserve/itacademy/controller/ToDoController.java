package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.impl.ToDoServiceImpl;
import com.softserve.itacademy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

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
    @Autowired
    UserServiceImpl userService;


    @GetMapping("/{id}/update/{id_user}")
    public String update(@PathVariable Integer id, Model model,  @PathVariable(name = "id_user")  Integer user_id) {
        model.addAttribute("todo", toDoService.readById(id));
        model.addAttribute("id_user",user_id);
        return "update-todo";
    }

    @GetMapping("/create/{id_user}")
    public String create( Model model, @PathVariable(name = "id_user")  Integer user_id) {
        model.addAttribute("id_user",user_id);
        return "create-todo";
    }

    @PostMapping("/creating/{id_user}")
    public String creatingToDo(  @RequestParam(name="title") String title,
                                  @RequestParam(name="owner") String owner,
                                  Model model, @PathVariable(name = "id_user")  Integer user_id) {
        ToDo toDo = new ToDo();
        toDo.setTitle(title);
        toDo.setCreatedAt(LocalDateTime.now());
        toDo.setOwner(userService.readById(Long.parseLong(owner)));
        toDoService.create(toDo);
        return "redirect:/todos/all/"+user_id;
    }


    @GetMapping("/{id}/delete/{id_user}")
    public String delete(@PathVariable Integer id,
                         @PathVariable(name = "id_user")  Integer user_id,
                         Model model) {

        toDoService.delete(id);

        return "redirect:/todos/all/"+user_id;
    }

    @PostMapping("/update/{id_user}")
    public String updatingToDo(@RequestParam(name = "title") String name,
                               @RequestParam(name = "id") long id,
                               @ModelAttribute(name="todo") ToDo toDo,
                               @PathVariable()  Integer id_user) {
        ToDo newTodo = toDoService.readById(id);
        newTodo.setTitle(name);
        toDoService.update(newTodo);
        return "redirect:/todos/all/" + id_user;
    }

    @GetMapping("/all/{id}")
    public String todosPage(@PathVariable()Integer id, Model model) {
        model.addAttribute("userID",id);
        model.addAttribute("userName",userService.readById(id).getFirstName() + " " + userService.readById(id).getLastName());
        model.addAttribute("todos", toDoService.getAll());
        model.addAttribute("counter", new Counter());
        return "todo-lists";
    }
}
