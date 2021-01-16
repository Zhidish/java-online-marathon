package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class RoleRepositoryTests {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void checkCreateRole() {
        Role role = new Role();
        role.setName("RoleCreate");
        long test_id = new Double(Math.random()*100).longValue();
        role.setId(test_id);
        role = roleRepository.save(role);
        assertEquals(test_id, role.getId());
    }
    @Test
    public void checkDeleteRoleByRole(){
        Role role = new Role();
        long test_id = new Double(Math.random()*100).longValue();
        role.setName("RoleDelete");
        role.setId(test_id);
        role = roleRepository.save(role);
        roleRepository.delete(role);
        assertFalse(roleRepository.existsById(role.getId()));
    }
    @Test
    public void checkDeleteRoleById(){
        Role role = new Role();
        long test_id = new Double(Math.random()*100).longValue();
        role.setName("RoleDeleteByID");
        role.setId(test_id);
        role = roleRepository.save(role);
        assertEquals(test_id, role.getId());
        roleRepository.deleteById(role.getId());
        assertFalse(roleRepository.existsById(role.getId()));
    }
    @Test
    public void checkDeleteAllRoles(){
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            role.setName("RoleDelete" + i);
            role.setId(test_id);
            roleRepository.save(role);
        }
        assertEquals(roleRepository.count(), 10);
        assertEquals(roleRepository.findAll().size(), 10);
        roleRepository.deleteAll();
        assertEquals(roleRepository.count(), 0);
        assertEquals(roleRepository.findAll().size(), 0);
    }
    @Test
    public void checkExistsRole(){
        Role role = new Role();
        long test_id = new Double(Math.random()*100).longValue();
        role.setName("RoleExists");
        role.setId(test_id);
        roleRepository.save(role);
        assertTrue(roleRepository.findAll().stream().anyMatch(role1 -> role1.getId() == role.getId()));
        assertTrue(roleRepository.existsById(role.getId()));
    }
    @Test
    public void checkFindRole(){
        Role role = new Role();
        long test_id = new Double(Math.random()*100).longValue();
        role.setName("RoleFind");
        role.setId(test_id);
        roleRepository.save(role);
        assertTrue(roleRepository.findAll().stream().anyMatch(role1 -> role1.getId() == role.getId()));
        assertEquals(role, roleRepository.findById(role.getId()).get());
    }
    @Test
    public void checkReturnAll(){
        List<Role> expectedRoles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            role.setName("Role" + i);
            role.setId(test_id);
            roleRepository.save(role);
            expectedRoles.add(role);
        }
        assertEquals(roleRepository.count(), 10);
        assertEquals(roleRepository.findAll().size(), 10);
        assertEquals(expectedRoles, roleRepository.findAll());
    }
    @Test
    public void checkCounter(){
        Set<Role> expectedRoles = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            role.setName("Role" + i);
            role.setId(test_id);
            roleRepository.save(role);
            expectedRoles.add(role);
        }
        assertEquals(roleRepository.count(), roleRepository.findAll().size(), expectedRoles.size());
        assertEquals(roleRepository.count(), expectedRoles.size());
        assertEquals(roleRepository.findAll().size(), expectedRoles.size());
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            role.setName("Role" + i*10);
            role.setId(test_id);
            roleRepository.save(role);
            expectedRoles.add(role);
        }
        assertEquals(roleRepository.count(), roleRepository.findAll().size(), expectedRoles.size());
        assertEquals(roleRepository.count(), expectedRoles.size());
        assertEquals(roleRepository.findAll().size(), expectedRoles.size());
    }
}

