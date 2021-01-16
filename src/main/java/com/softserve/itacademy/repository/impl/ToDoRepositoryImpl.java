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


import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class ToDoRepositoryImpl implements ToDoRepository {


    static SessionFactory sessionFactory = new AppContext().getSessionFactory();


    @Override
    public List<ToDo> findAll() {
        List<ToDo> toDos = null;

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        try {
            toDos = session.createQuery("from  ToDo ").list();
        }catch(NoResultException e ){}
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
        try {
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
        } catch (Exception e) {


        }

    }

    @Override
    public void delete(ToDo toDo) {
        deleteById(toDo.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends ToDo> iterable) {

    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        try {
            findAll().forEach(toDo -> delete(toDo));
        } catch (NullPointerException e) {


        }

    }

    @Override
    public <S extends ToDo> S save(S s) {
        if (existsById(s.getId())) {
            try {
                updateToDo(s);
            } catch (Exception e) {

            }
        } else {

            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.save(s);
            session.getTransaction().commit();
            session.close();
        }
        return (S) findById(s.getId()).get();

    }

    @Override
    public <S extends ToDo> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<ToDo> findById(Long aLong) throws NullPointerException {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Optional<ToDo> toDoOptional = null;
        try {
            toDoOptional = Optional.of((ToDo) session.createQuery("SELECT toDo FROM ToDo  toDo where toDo.id=:id")
                    .setParameter("id", aLong).getSingleResult());
        } catch (Exception e) {


        }

        session.getTransaction().commit();
        session.close();

        return toDoOptional;
    }

    @Override
    public boolean existsById(Long aLong) {

        try {
            return findById(aLong).isPresent();
        } catch (Exception e) {

        }
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
        try {
            return findById(aLong).get();
        }catch(NullPointerException e ){
            return null;
        }
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

            try {
            Query query = session.createQuery("UPDATE ToDo  SET createdAt=:createdAt,   owner=:owner,  title=:title where id=:id")
                    .setParameter("owner", todo.getOwner())
                    .setParameter("createdAt", todo.getCreatedAt())
                    .setParameter("title", todo.getTitle())
                    .setParameter("id", todo.getId());

            query.executeUpdate();
            } catch (Exception e) {


            }

            session.getTransaction().commit();
            session.close();


    }

    @Override
    public List<ToDo> getAllByUser(User user) {
        List<ToDo> toDos = null;
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        try {
            toDos = session.createQuery("FROM ToDo todo  where  todo.owner.id=:id")
                    .setParameter("id", user.getId()).list();
        } catch (NoResultException e) {

        }
        session.getTransaction().commit();
        session.close();
        return toDos;

    }
}
