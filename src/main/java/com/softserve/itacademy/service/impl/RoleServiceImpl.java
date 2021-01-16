package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.repository.impl.RoleRepositoryImpl;
import com.softserve.itacademy.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    RoleRepositoryImpl roleRepository = new RoleRepositoryImpl();

    @Override
    public Role create(Role role) {
        if (roleRepository.existsById(role.getId())){
            update(role);
        }
        else {
            roleRepository.save(role);
        }
        return roleRepository.findById(role.getId()).get();
    }

    @Override
    public Role readById(long id) {
        if (roleRepository.existsById(id)){
            return roleRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Role update(Role role) {
        if (roleRepository.existsById(role.getId())) {
            return roleRepository.save(role);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        if (roleRepository.existsById(id)){
            roleRepository.deleteById(id);
        }
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
