package com.softserve.itacademy.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Scope("prototype")
public class User {


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ToDo> getMyTodos() {
        return myTodos;
    }

    public void setMyTodos(List<ToDo> myTodos) {
        this.myTodos = myTodos;
    }

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private List<ToDo> myTodos;

    // Constructor(s), getters, setters, hashCode, equals, etc.

}
