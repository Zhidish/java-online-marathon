package com.softserve.itacademy.controller;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.RoleService;
import com.softserve.itacademy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") User user, BindingResult result) throws EntityNotFoundException {
        if (result.hasErrors()) {
            return "create-user";
        }
        try {
            user.setPassword(user.getPassword());
            user.setRole(roleService.readById(2));
            User newUser = userService.create(user);


            return "redirect:/todos/all/users/" + newUser.getId();
        }catch (NoSuchElementException e ){

            throw  new EntityNotFoundException("no role found in DB");
        }

    }

    @GetMapping("/{id}/read")
    public String read(@PathVariable long id, Model model) throws EntityNotFoundException {
        try {
            User user = userService.readById(id);
            model.addAttribute("user", user);
            return "user-info";
        }catch(NoSuchElementException e ){
            throw new EntityNotFoundException("no user foudn in DB");

        }
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable long id, Model model) throws EntityNotFoundException {
        try {
            User user = userService.readById(id);
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.getAll());
            return "update-user";
        }catch(NoSuchElementException e ){

            throw  new EntityNotFoundException("no user found in DB");
        }
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) throws EntityNotFoundException {
        try {
            userService.delete(id);
        }catch(NoSuchElementException e ){
            throw new EntityNotFoundException("no user found in DB"+ e.getClass());
        }
        return "redirect:/users/all";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users-list";
    }
}
