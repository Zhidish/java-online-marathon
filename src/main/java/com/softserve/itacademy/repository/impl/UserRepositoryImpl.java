package com.softserve.itacademy.repository.impl;

import com.softserve.itacademy.config.AppContext;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {


    static SessionFactory sessionFactory = new AppContext().getSessionFactory();


    @Override
    public List<User> findAll() throws NullPointerException {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<User> users = null;
        try {
            users = (List<User>) session.createQuery("from User ").list();
        } catch (NoResultException e) {


        }
        session.getTransaction().commit();
        session.close();

        return users;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        try {
            return findAll().size();
        } catch (NullPointerException e) {


            return 0;
        }

    }

    @Override
    public void deleteById(Long aLong) {
        User user = new User();
        user.setId(aLong);
        delete(user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        user = findById(user.getId()).get();
        try {
            session.createSQLQuery("DELETE from todo_collaborator WHERE collaborator_id= " + user.getId()).executeUpdate();

            user.getMyTodos().forEach(toDo -> session.createSQLQuery("DELETE from tasks WHERE todo_id= " + toDo.getId()).executeUpdate());


            session.createQuery("DELETE ToDo todo  WHERE todo.owner.id=:id")
                    .setParameter("id", user.getId()).executeUpdate();


            session.createQuery("DELETE User user  WHERE user.id=:id")
                    .setParameter("id", user.getId()).executeUpdate();
        } catch (Exception e) {
        }


        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        findAll().forEach(user -> deleteById(user.getId()));
        session.close();

    }

    @Override
    public <S extends User> S save(S s) {
        if (existsById(s.getId())) {
            updateUser(s);

        } else {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.save(s);
            session.getTransaction().commit();
        }

        return (S) findById(s.getId()).get();
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) throws NullPointerException {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Optional<User> user = null;
        try {
            user = Optional.of((User) session.createQuery("SELECT user from User user WHERE user.id=:id").setParameter("id", aLong).getSingleResult());
        } catch (NoResultException e) {
        }
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public boolean existsById(Long aLong) {
        try {
            return findById(aLong).isPresent();
        } catch (NullPointerException e) {
            return false;
        }


    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<User> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Long aLong) {
        try {
            return findById(aLong).get();
        } catch (NullPointerException e) {
            return null;

        }

    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public User getUserByEmail(User user) throws NullPointerException {
        if (user == null) {
            return null;
        }
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        User user1 = null;
        try {
            user1 = (User) session.createQuery("SELECT user FROM User user where user=:email").setParameter("email", user.getEmail()).getSingleResult();
        } catch (NoResultException e) {
        }

        session.getTransaction().commit();
        session.close();
        return user1;

    }

    @Override
    public User updateUser(User user) {

        Session session = sessionFactory.openSession();


        session.getTransaction().begin();
        try {
            session.createQuery("UPDATE User  SET firstName=:firstName, lastName=:lastName, email=:email, password=:password where id=:id")
                    .setParameter("firstName", user.getFirstName())
                    .setParameter("lastName", user.getLastName())
                    .setParameter("email", user.getEmail())
                    .setParameter("password", user.getPassword())
                    .setParameter("id", user.getId()).executeUpdate();
        } catch (Exception e) {


        }
        session.getTransaction().commit();
        session.close();

        return getOne(user.getId());
    }

}
