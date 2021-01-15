package com.softserve.itacademy.repository;

import com.softserve.itacademy.repository.impl.RoleReposetoryimpl;

public class Main {


    public static void main(String[] args) {
        RoleReposetoryimpl roleReposetoryimpl = new RoleReposetoryimpl();
        System.out.println(  roleReposetoryimpl.findAll().toString());


    }
}
