package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.impl.RoleRepositoryImpl;
import com.softserve.itacademy.repository.impl.ToDoRepositoryImpl;
import com.softserve.itacademy.repository.impl.UserRepositoryImpl;

public class Main {


    public static void main(String[] args) {
        RoleRepositoryImpl roleReposetoryimpl = new RoleRepositoryImpl();
        UserRepository userRepositoryImpl = new UserRepositoryImpl();
        ToDoRepository toDoRepository = new ToDoRepositoryImpl();
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
        user.setId(5L);
        user.setFirstName("Rostya");
        user.setLastName("Shynko");
        user.setEmail("doublelongword@gmail.com");
        user.setRole(roleReposetoryimpl.getOne(1L));
        user.setPassword("$2a$10$CdEJ2PKXgUCIwU4pDQWICuiPjxb1lysoX7jrN");
  /*
        // userRepositoryImpl.delete(user);

        //  userRepositoryImpl.deleteById(5L);
         userRepositoryImpl.save(user);
        user.setFirstName("BoyNextDoor");
            userRepositoryImpl.updateUser(user);*/

        ToDo toDo = new ToDo();
        toDo.setId(7);
        toDo.setOwner(user);
        toDo.setTitle("Hard task");


        System.out.println(toDoRepository.findById(13L).get().toString());
        //  toDoRepository.save(toDo);
        //  toDoRepository.deleteById(7L);

       /* System.out.println(toDoRepository.getOne(8L).getCreatedAt());
        System.out.println(toDoRepository.getAllByUser(user).toString());*/
        toDoRepository.updateToDo(toDo);



    }
}
