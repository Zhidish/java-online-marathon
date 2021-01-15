package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.impl.RoleRepositoryImpl;

public class Main {


    public static void main(String[] args) {
        RoleRepositoryImpl roleReposetoryimpl = new RoleRepositoryImpl();
        System.out.println(roleReposetoryimpl.findAll().toString());


        System.out.println(roleReposetoryimpl.getOne(1L).toString());

        Role role = new Role();
        role.setName("nuller");
        roleReposetoryimpl.delete(role);
        roleReposetoryimpl.deleteById(5L);

        roleReposetoryimpl.save(role);

        System.out.println(  roleReposetoryimpl.getAllRolesByUsers().toString());

    }
}
