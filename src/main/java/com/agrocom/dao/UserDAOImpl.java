package com.agrocom.dao;

import com.agrocom.model.User;

import org.hibernate.*;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public User getUser(Long userId) {
        return (User) getCurrentSession().get(User.class, userId);
    }

    public User getUserByEmail(String email) {
        List users = getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .setFetchMode("ownedSocieties", FetchMode.JOIN)
                .setFetchMode("infields", FetchMode.JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();

        return users.size() > 0 ? (User) users.get(0) : null;

/*
        List userList = new ArrayList<>();
        Query query;

        try {
            query = getCurrentSession().createQuery("from com.agrocom.model.User u where email = :email");
            query.setParameter("email", email);
            userList = query.list();
        } catch (QueryException e) {
            logger.warn(e.getMessage());
        }

        if (userList.size() > 0) {
            User user = (User) userList.get(0);
//            user.getInfields().size();
//            user.getOwnedSocieties().size();
            return user;
        }
        else
            return null;*/
    }

    public List<User> searchUserByFirstName(String firstName) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("firstName", firstName));
        return criteria.list();
    }

    public List<User> searchUserByLastName(String lastName) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("lastName", lastName));
        return criteria.list();
    }

    public User getUserByToken(String token) {
        List userList = new ArrayList<>();
        Query query;

        try {
            query = getCurrentSession().createQuery("from com.agrocom.model.User u where token = :token");
            query.setParameter("token", token);
            userList = query.list();
        } catch (QueryException e) {
            logger.warn(e.getMessage());
        }

        if (userList.size() > 0)
            return (User) userList.get(0);
        else
            return null;
    }

    public User getUserByPin(String pin) {
        List userList = new ArrayList<>();
        Query query;

        try {
            query = getCurrentSession().createQuery("from com.agrocom.model.User u where pin = :pin");
            query.setParameter("pin", pin);
            userList = query.list();
        } catch (QueryException e) {
            logger.warn(e.getMessage());
        }

        if (userList.size() > 0)
            return (User) userList.get(0);
        else
            return null;
    }

    public User getUserByFirstAndLastName(String firstName, String lastName) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.and(
                        Restrictions.eq("firstName", lastName),
                        Restrictions.eq("lastName", lastName)
                )
        );
        return criteria.list().size() > 0 ? (User) criteria.list().get(0) : null;
    }

    public Long addUser(User user) {
        Long savedUserId = -1l;

        try {
            savedUserId = (Long) getCurrentSession().save(user);
            getCurrentSession().flush();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return savedUserId;
    }

    public Boolean updateUser(User user) {
        try {
            getCurrentSession().getTransaction().begin();
            getCurrentSession().update(user);
            getCurrentSession().getTransaction().commit();
            getCurrentSession().flush();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteUser(User user) {
        try {
            getCurrentSession().getTransaction().begin();
            getCurrentSession().delete(user);
            getCurrentSession().getTransaction().commit();
            getCurrentSession().flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
