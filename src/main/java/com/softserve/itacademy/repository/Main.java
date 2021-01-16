package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.impl.RoleRepositoryImpl;
import com.softserve.itacademy.repository.impl.UserRepositoryImpl;

public class Main {


    public static void main(String[] args) {
        RoleRepositoryImpl roleReposetoryimpl = new RoleRepositoryImpl();
        UserRepository userRepositoryImpl = new UserRepositoryImpl();

        /*System.out.println(roleReposetoryimpl.findAll().toString());


        System.out.println(roleReposetoryimpl.getOne(1L).toString());

        Role role = new Role();
        role.setName("nuller");
        roleReposetoryimpl.delete(role);
        roleReposetoryimpl.deleteById(5L);

        roleReposetoryimpl.save(role);
*/
        /*  System.out.println(  roleReposetoryimpl.getAllRolesByUsers().toString());
         */
//System.err.println(  roleReposetoryimpl.getAllRolesByUsers().toString());

        /*   System.out.println(userRepositoryImpl.findAll().toString());*/

        User user = new User();
        user.setId(11L);
        user.setFirstName("Rostya");
        user.setLastName("Shynko");
        user.setEmail("doublelongword@gmail.com");
        user.setRole(roleReposetoryimpl.getOne(1L));
        user.setPassword("$2a$10$CdEJ2PKXgUCIwU4pDQWICuiPjxb1lysoX7jrN");

        // userRepositoryImpl.delete(user);

        //  userRepositoryImpl.deleteById(5L);
        //  userRepositoryImpl.save(user);
        user.setFirstName("BoyNextDoor");
            userRepositoryImpl.updateUser(user);

    }
}
