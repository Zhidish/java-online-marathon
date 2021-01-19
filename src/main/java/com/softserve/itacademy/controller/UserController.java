package com.softserve.itacademy.controller;

import com.softserve.itacademy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    class Counter {
      private  int counter = 0;

        Counter() {

        }

        private void count() {
            counter++;


        }
        public int  getCounter(){
             this.count();
            return  counter;
        }

    }

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/")
    public String usersPage(Model model) {
        int counter = 0;
        model.addAttribute("users", userService.getAll());
        model.addAttribute("counter" ,new Counter());
        return "users-list";
    }

    @GetMapping("/create")
    public String create() {

        return "";
    }

    //    @PostMapping("/create")
//    public String create(//add needed parameters) {
//
//        return " ";
//    }
//
//    @GetMapping("/{id}/read")
//    public String read(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
//
    @GetMapping("/{id}/update")
    public String update() {

        return " ";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id ) {
            userService.delete(id);
        return "redirect:/";
    }

//    @GetMapping("/all")
//    public String getAll(//add needed parameters) {
//        //ToDo
//        return " ";
//    }
}
