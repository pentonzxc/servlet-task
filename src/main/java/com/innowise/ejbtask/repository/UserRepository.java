package com.innowise.ejbtask.repository;


import com.innowise.ejbtask.DatabaseConfig;
import com.innowise.ejbtask.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
public class UserRepository {

    private SessionFactory factory;


    @PostConstruct
    private void init() {
        factory = DatabaseConfig.buildSessionFactory();
    }

    public Optional<User> findByName(String username) {
        Transaction transaction = null;
        User user = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);

            cr.select(root).where(cb.equal(root.get("name"), username));

            user = session.createQuery(cr).getSingleResult();

            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
        }

        return Optional.ofNullable(user);
    }


    public List<User> findAll() {
        Transaction transaction = null;
        List<User> users = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            criteria.from(User.class);

            users = session.createQuery(criteria).getResultList();

            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
        }

        return users;
    }

    public void save(User user){
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            session.persist(user);

            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
        }
    }
}
