package com.agrocom.dao;

import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.model.UserSociety;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class UserSocietyDAOImpl implements UserSocietyDAO {

    Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public UserSociety getUserSociety(Long id) {
        return (UserSociety) getCurrentSession().load(UserSociety.class, id);
    }

    // todo
    @Override
    public List<Society> getSocietiesByUser(User user) {
        Criteria criteria = getCurrentSession().createCriteria(UserSociety.class);
        criteria.add(Restrictions.eq("user", user));

        return criteria.list().size() > 0 ? criteria.list() : null;
    }

    @Override
    public UserSociety getUserSocietyByUserAndSociety(User user, Society society) {
        Criteria criteria = getCurrentSession().createCriteria(UserSociety.class);
        criteria.add(Restrictions.eq("user", user));
        criteria.add(Restrictions.eq("society", society));

        return criteria.list().size() > 0 ? (UserSociety) criteria.list().get(0) : null;

        /*
        List userList = new ArrayList<>();
        Query query;

        try {
            query = getCurrentSession()
                    .createQuery("from com.agrocom.model.UserSociety u "
                            + " where user = :user AND society = :society");
            query.setParameter("user", user);
            query.setParameter("society", society);
            userList = query.list();
        } catch (QueryException e) {
            logger.warn(e.getMessage());
        }

        if (userList.size() > 0)
            return (UserSociety) userList.get(0);
        else
            return null;*/
    }

    @Override
    public List<UserSociety> getUserSocietyByUser(User user) {
        Criteria criteria = getCurrentSession().createCriteria(UserSociety.class);
        criteria.add(Restrictions.eq("user", user));

        return criteria.list();
        /*
        List userList = new ArrayList<>();
        Query query;

        try {
            query = getCurrentSession()
                    .createQuery("from com.agrocom.model.UserSociety u where user = :user");
            query.setParameter("user", user);
            userList = query.list();
        } catch (QueryException e) {
            logger.warn(e.getMessage());
        }

        return userList;*/
    }

    public Long addUserSociety (UserSociety userSociety) {
        Long savedUserId = -1l;

        try {
            savedUserId = (Long) getCurrentSession().save(userSociety);
            getCurrentSession().flush();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return savedUserId;
    }

    public Boolean updateUserSociety(UserSociety userSociety) {
        try {
            getCurrentSession().getTransaction().begin();
            getCurrentSession().update(userSociety);
            getCurrentSession().getTransaction().commit();
            getCurrentSession().flush();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
