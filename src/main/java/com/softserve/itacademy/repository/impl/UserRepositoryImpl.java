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

import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {


    static SessionFactory sessionFactory = new AppContext().getSessionFactory();


    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<User> users = (List<User>) session.createQuery("from User ").list();

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

        return findAll().size();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User user) {


        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();

        user = (User) session.createQuery("SELECT user from User user WHERE user.id=:id").setParameter("id", user.getId()).getSingleResult();

        System.err.println(user.getEmail());
        System.err.println(user.getId());
        System.err.println(user.getMyTodos().get(0).getOwner().getId());
       user.getMyTodos().forEach(toDo -> session.createSQLQuery("DELETE from tasks WHERE todo_id= " + toDo.getId()).executeUpdate());
      session.getTransaction().commit();

//
//        session.createSQLQuery("DELETE from todo_collaborator WHERE collaborator_id= " + user.getId());
//
//
//        session.createQuery("DELETE ToDo todo  WHERE todo.owner.id=:id")
//                .setParameter("id", user.getId()).executeUpdate();
//
//
//        session.createQuery("DELETE User user  WHERE user.id=:id")
//                .setParameter("id", user.getId()).executeUpdate();

        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends User> S save(S s) {

        Session  session = sessionFactory.openSession();

        return null;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
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
        return null;
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
    public User getUserByEmail(User user) {
        if (user == null) {
            return null;
        }
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        User user1 = (User) session.createQuery("SELECT user FROM User user where user=:email").setParameter("email", user.getEmail()).getSingleResult();
        System.out.println(user1.getId());
        session.getTransaction().commit();
        session.close();
        return user1;

    }
}
