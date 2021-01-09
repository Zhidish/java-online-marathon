package com.softserve.itacademy.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
public class User {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private int id;

    private static int counter;


    private List<ToDo> myTodos;

    public User(String firstName, String lastName, String email, String password, List<ToDo> myTodos) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.myTodos = myTodos;
        this.id = counter++;
    }

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

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return "User" + this.id + " (" + this.email +")";
    }

    @Override
    public boolean equals(Object o) {
        return this.id == ((User)o).getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, myTodos);
    }

}
