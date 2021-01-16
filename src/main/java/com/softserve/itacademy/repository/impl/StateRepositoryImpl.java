package com.softserve.itacademy.repository.impl;

import com.softserve.itacademy.config.AppContext;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.NoResultException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StateRepositoryImpl implements StateRepository {

    static SessionFactory sessionFactory = new AppContext().getSessionFactory();

    @Override
    public List<State> findAll() throws NullPointerException {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<State> state = null;

        try {
            state = session.createQuery("FROM  State ").list();
        } catch (NoResultException e) {
        }
        session.getTransaction().commit();
        session.close();
        return state;
    }

    @Override
    public List<State> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<State> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<State> findAllById(Iterable<Long> iterable) {
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
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        try {
            session.createQuery("DELETE Task task  WHERE task.state.id=:id").setParameter("id", aLong).executeUpdate();
            session.createQuery("DELETE State  state WHERE state.id=:id").setParameter("id", aLong).executeUpdate();
        } catch (Exception e) {
        }
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(State state) {
        deleteById(state.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends State> iterable) {

    }

    @Override
    public void deleteAll() {
        findAll().forEach(state -> delete(state));

    }

    @Override
    public <S extends State> S save(S s) {

        if (existsById(s.getId())) {
            updateState(s);
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
    public <S extends State> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<State> findById(Long aLong) throws NullPointerException {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Optional<State> state = Optional.empty();
        try {
            state = Optional.of(
                    (State) session.createQuery("SELECT state1 FROM State state1 where state1.id=:id").setParameter("id", aLong).getSingleResult()
            );
        } catch (NoResultException e) {


        }

        session.getTransaction().commit();
        session.close();
        return state;
    }

    @Override
    public boolean existsById(Long aLong) {
        return findById(aLong).isPresent();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends State> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<State> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public State getOne(Long aLong) {
        try {
            return findById(aLong).get();
        }catch(NullPointerException e ){

            return null;
        }
    }

    @Override
    public <S extends State> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends State> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends State> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends State> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends State> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends State> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public void updateState(State state) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        try {
            session.createQuery("UPDATE State SET name= " + state.getName()).executeUpdate();
        }catch (Exception e ){}
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public State getStateByName(String name) {
        Session session = sessionFactory.openSession();

        session.getTransaction().begin();
        State state=null;
        try {
            state = (State) session.createQuery("FROM State where name=" + name).getSingleResult();
        }catch(NoResultException e ){}
        session.getTransaction().commit();
        session.close();

        return state;
    }

    @Override
    public List<State> getAllStatesSortedByName() {

        Comparator<State> comparator = (o1, o2) -> o1.getName().compareTo(o2.getName());

        List<State> statesSortedBy = findAll();
        statesSortedBy.sort(comparator);
        return statesSortedBy;
    }
}
