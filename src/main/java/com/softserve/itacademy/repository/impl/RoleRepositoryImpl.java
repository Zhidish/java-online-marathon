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
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {


    static SessionFactory sessionFactory = new AppContext().getSessionFactory();

    public List<Role> getAllRolesByUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        List<Role> roles = null;
        try {
            roles = (List<Role>) session.createQuery("select role From Role role left join fetch role.users").list();
        } catch (NoResultException e) {

        }

        session.getTransaction().commit();
        session.close();

        return roles;
    }


    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Role> roles = null;
        try {
            roles = (List<Role>) session.createQuery("from Role ").list();
        } catch (NoResultException e) {
        }


        session.getTransaction().commit();
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


        return null;
    }

    @Override
    public long count() {
        long result = 0;
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        try {
            result = session.createQuery("from Role").list().size();
        } catch (NoResultException e) {


        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        try {
            List<User> users = (List<User>) session.createQuery("SELECT user FROM User user where user.role.id=:id" ).setParameter("id",aLong)
                    .list();
            for (User user : users) {

                session.createSQLQuery("DELETE from todo_collaborator WHERE collaborator_id= " + user.getId()).executeUpdate();

                user.getMyTodos().forEach(toDo -> session.createSQLQuery("DELETE from tasks WHERE todo_id= " + toDo.getId()).executeUpdate());


                session.createQuery("DELETE ToDo todo  WHERE todo.owner.id=:id")
                        .setParameter("id", user.getId()).executeUpdate();


                session.createQuery("DELETE User user  WHERE user.id=:id")
                        .setParameter("id", user.getId()).executeUpdate();


            }
            session.createQuery("DELETE FROM Role r where r.id=" + aLong).executeUpdate();


        } catch (Exception e) {
        }
        session.getTransaction().commit();
        session.close();


    }

    @Override
    @Transactional
    public void delete(Role role) {
        deleteById(role.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends Role> iterable) {

    }


    @Override
    public void deleteAll() {
        try {
            findAll().forEach(role -> deleteById(role.getId()));
        }catch (NullPointerException e ){



        }

    }

    @Override
    public <S extends Role> S save(S s) {
        if (existsById(s.getId())) {
            updateRole(s);
        } else {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.save(s);
            session.getTransaction().commit();
        }
        return (S) findById(s.getId()).get();
    }

    @Override
    public void updateRole(Role role) {
        Session session = sessionFactory.openSession();
        System.err.println(role.getId());
        session.getTransaction().begin();
        try {
            Query query = session.createQuery("UPDATE Role  SET name=:name where id=:id")
                    .setParameter("name", role.getName())
                    .setParameter("id", role.getId());
        } catch (Exception e) {
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public <S extends Role> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Role> findById(Long aLong) {

        Optional<Role> role = null;

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            role = Optional.of((Role) session.createQuery(" SELECT a  from Role a  where a.id=:Id")
                    .setParameter("Id", aLong).getSingleResult());

        } catch (NoResultException e) {
        }

        session.getTransaction().commit();
        session.close();
        return role;


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

        try {
            return findById(aLong).get();
        } catch (NullPointerException e) {

            return null;
        }
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
