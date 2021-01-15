package com.softserve.itacademy.repository.impl;

import com.softserve.itacademy.config.AppContext;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.RoleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {


    static SessionFactory sessionFactory = new AppContext().getSessionFactory();

    public List<Role> getAllRolesByUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Role> roles = (List<Role>) session.createQuery("select role From Role role left join fetch role.users").list();
        session.getTransaction().commit();
        session.close();

        return roles;
    }


    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        System.out.println(session.isOpen());
        List<Role> roles = (List<Role>) session.createQuery("from Role ").list();
        session.close();

        return roles;


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

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Role a where a.id=:id");
        query.setParameter("id", iterable.iterator().next());

        return null;
    }

    @Override
    public long count() {
        Session session = sessionFactory.openSession();
        long result = session.createQuery("from Role").list().size();
        session.close();
        return result;
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.createQuery(
                "delete Role  " +
                        "where id=:Id")
                .setParameter("Id", aLong)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();


    }

    @Override
    @Transactional
    public void delete(Role role) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.createQuery(
                "delete Role r  " +
                        "where r.name = :name or r.id=:Id")
                .setParameter("name", role.getName())
                .setParameter("Id", role.getId())
                .executeUpdate();

        session.getTransaction().commit();
    }

    @Override
    public void deleteAll(Iterable<? extends Role> iterable) {

    }


    @Override
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.createQuery(
                "delete Role r ")
                .executeUpdate();

        session.getTransaction().commit();

    }

    @Override
    public <S extends Role> S save(S s) {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(s);
        session.getTransaction().commit();


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
        return findById(aLong).isPresent();

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

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(" SELECT a  from Role a  where a.id=:Id", Role.class);
        query.setParameter("Id", aLong);
        Role role = (Role) query.getSingleResult();

        return role;
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
