package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.impl.RoleServiceImpl;
import com.softserve.itacademy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {



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
    RoleServiceImpl roleServiceimpl;





    @Autowired
    UserServiceImpl userService;

    @PostMapping("/update")
    public String updatingUser(@ModelAttribute(name="user") User user) {

        System.err.println(user.getId());
        System.err.println(user.getFirstName());
        userService.update(user);
        return "redirect:/users/all";
    }

    @GetMapping("/all")
    public String usersPage(Model model) {

        model.addAttribute("users", userService.getAll());
        model.addAttribute("counter", new Counter());
        return "users-list";
    }

    @GetMapping("/create")
    public String create() {

        return "create-user";
    }

    @PostMapping("/create")
    public String create_(
            @RequestParam(name="firstName") String firstName,
            @RequestParam(name="lastName") String lastName,
            @RequestParam(name="email") String email,
            @RequestParam(name="password") String password,
            Model model
    )
    {
        Role role = roleServiceimpl.readById(2L);


      User user = new User();
      user.setFirstName(firstName);
      user.setLastName(lastName);
      user.setEmail(email);
      user.setPassword(password);
      user.setRole(role);


    userService.create(user);

        return "redirect:/users/all";
    }

    @GetMapping("/{id}/read")
    public String read() {
        //ToDo
        return " ";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.readById(id));

        return "update-user";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/users/all";
    }

}
