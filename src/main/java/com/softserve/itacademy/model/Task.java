package com.softserve.itacademy.model;

import java.util.Objects;

public class Task {

    private String name;

    private Priority priority;

    private int id;

    private static int counter;

    public Task(String name, Priority priority) {
        this.name = name;
        this.priority = priority;
        this.id = counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return this.getId() == ((Task) o).getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priority);
    }
}
