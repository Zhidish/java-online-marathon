package com.softserve.itacademy.repository.impl;

import com.softserve.itacademy.config.AppContext;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.ToDoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

public class ToDoRepositoryImpl implements ToDoRepository {


    static SessionFactory sessionFactory = new AppContext().getSessionFactory();


    @Override
    public List<ToDo> findAll() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        List<ToDo> toDos;
        toDos = session.createQuery("from  ToDo ").list();

        session.getTransaction().commit();
        session.close();
        return toDos;


    }

    @Override
    public List<ToDo> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ToDo> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ToDo> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.createSQLQuery(String.format("DELETE from todo_collaborator where todo_id=%d ", aLong)).executeUpdate();


        session.createQuery("DELETE FROM Task task where task.toDo.id=:id")
                .setParameter("id", aLong).executeUpdate();


        session.createQuery("DELETE from ToDo where id=:id ")
                .setParameter("id", aLong).
                executeUpdate();

        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void delete(ToDo toDo) {

    }

    @Override
    public void deleteAll(Iterable<? extends ToDo> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ToDo> S save(S s) {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.save(s);


        session.getTransaction().commit();
        session.close();
        return (S) findById(s.getId()).get();

    }

    @Override
    public <S extends ToDo> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<ToDo> findById(Long aLong) {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Optional<ToDo> toDoOptional = Optional.of((ToDo) session.createQuery("SELECT toDo FROM ToDo  toDo where toDo.id=:id")
                .setParameter("id", aLong).getSingleResult());


        session.getTransaction().commit();
        session.close();

        return toDoOptional;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ToDo> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<ToDo> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ToDo getOne(Long aLong) {

        return findById(aLong).get();
    }

    @Override
    public <S extends ToDo> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ToDo> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ToDo> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ToDo> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ToDo> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ToDo> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public void updateToDo(ToDo todo) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        System.err.println(todo.getId());
     Query query= session.createQuery("UPDATE ToDo  SET createdAt=:createdAt,    owner=:owner, tasks=:tasks where title='Mike`s To-Do #1'")
                .setParameter("owner", todo.getOwner())
                .setParameter("createdAt",todo.getCreatedAt())
                .setParameter("tasks", todo.getTasks())
              /*  .setParameter("title", todo.getTitle())*/
               /* .setParameter("id", todo.getId())*/;

     query.executeUpdate();

        session.getTransaction().commit();
        session.close();


    }

    @Override
    public List<ToDo> getAllByUser(User user) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<ToDo> toDos = session.createQuery("FROM ToDo todo  where  todo.owner.id=:id")
                .setParameter("id", user.getId()).list();

        session.getTransaction().commit();
        session.close();
        return toDos;


    }
}
