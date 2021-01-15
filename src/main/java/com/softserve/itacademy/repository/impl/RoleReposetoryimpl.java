package com.softserve.itacademy.repository.impl;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class RoleReposetoryimpl  implements RoleRepository {

    @Autowired
    SessionFactory sessionFactory;
    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(session.isOpen());
        return (List<Role>) session.createQuery("from Role ").list();
    }

    @Override
    public List<Role> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Role> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Role role) {

    }

    @Override
    public void deleteAll(Iterable<? extends Role> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Role> S save(S s) {
        return null;
    }

    @Override
    public <S extends Role> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Role> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Role> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Role> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Role getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Role> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Role> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Role> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Role> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Role> boolean exists(Example<S> example) {
        return false;
    }
}
