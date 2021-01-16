package com.softserve.itacademy.repository.impl;

import com.softserve.itacademy.config.AppContext;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class TaskRepositoryImpl implements TaskRepository {


    static SessionFactory sessionFactory = new AppContext().getSessionFactory();

    @Override
    public List<Task> findAll() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        List<Task> tasks = session.createQuery("FROM Task ").list();
        session.getTransaction().commit();
        session.close();
        return tasks;

    }

    @Override
    public List<Task> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Task> findAllById(Iterable<Long> iterable) {
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
        session.createQuery("DELETE FROM Task task where task.id=:id")
                .setParameter("id", aLong).executeUpdate();
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void delete(Task task) {

    }

    @Override
    public void deleteAll(Iterable<? extends Task> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Task> S save(S s) {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(s);
        session.getTransaction().commit();
        session.close();


        return null;
    }

    @Override
    public <S extends Task> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Task> findById(Long aLong) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Optional<Task> task = Optional.of(
                (Task) session.createQuery("SELECT Task FROM Task task where task.id=:id")
                        .setParameter("id", aLong));

        return task;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Task> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Task> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Task getOne(Long aLong) {
        return findById(aLong).get();

    }

    @Override
    public <S extends Task> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Task> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Task> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Task> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Task> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Task> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public void updateTask(Task task) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.createQuery("UPDATE Task SET state=:state, name=:name,priority=:priority")
                .setParameter("state", task.getState())
                .setParameter("name", task.getName())
                .setParameter("priority", task.getPriority()).executeUpdate();

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<Task> getTasksByToDoId(Long aLong) {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Task> tasks = session.createQuery("SELECT task FROM Task task where task.toDo.id=:toDoId")
                .setParameter("toDoId", aLong).list();
        session.getTransaction().commit();
        session.close();
        return tasks;

    }
}
