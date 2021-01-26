package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role readById(long id) {
        try {
            Optional<Role> optional = roleRepository.findById(id);

        return optional.get();
        }catch(NoSuchElementException e ){
            throw new EntityNotFoundException(" no role found in DB");

        }
    }

    @Override
    public Role update(Role role) {
        Role oldRole = readById(role.getId());
        return roleRepository.save(role);
    }

    @Override
    public void delete(long id) {
        try {
            Role role = readById(id);
            roleRepository.delete(role);

        }catch(NoSuchElementException e ){


            throw  new EntityNotFoundException(" no role found in DB ");

        }


    }

    @Override
    public List<Role> getAll() {

        try {
            List<Role> roles = roleRepository.findAll();
            return roles.isEmpty() ? new ArrayList<>() : roles;

        } catch(NoSuchElementException e ){
            throw new EntityNotFoundException("no roles found ");


        }
    }
}
