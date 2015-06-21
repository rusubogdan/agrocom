package com.agrocom.dao;

import com.agrocom.model.Society;

import com.agrocom.model.User;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SocietyDAOImpl implements SocietyDAO {

    Logger logger = LoggerFactory.getLogger(SocietyDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Society getSociety(Long societyId, Boolean fullData) {
        Criteria criteria = getCurrentSession().createCriteria(Society.class);
        criteria.add(Restrictions.eq("societyId", societyId));
        if (fullData) {
            criteria.setFetchMode("employees", FetchMode.JOIN);
            criteria.setFetchMode("infields", FetchMode.JOIN);
            criteria.setFetchMode("garages", FetchMode.JOIN);
        }

        return criteria.list().size() > 0 ? (Society) criteria.list().get(0) : null;
    }

    @Override
    public Society getSocietyByName(String societyName, Boolean fullData) {
        Criteria criteria = getCurrentSession().createCriteria(Society.class);
        criteria.add(Restrictions.eq("name", societyName));

        if (fullData) {
            criteria.setFetchMode("employees", FetchMode.JOIN);
            criteria.setFetchMode("infields", FetchMode.JOIN);
            criteria.setFetchMode("garages", FetchMode.JOIN);
        }
        return criteria.list().size() > 0 ? (Society) criteria.list().get(0) : null;
    }

    @Override
    public List<Society> getSocietiesByUser(User user, Boolean fullData) {
        Criteria criteria = getCurrentSession().createCriteria(Society.class);
        criteria.add(Restrictions.eq("owner", user));

        if (fullData) {
            criteria.setFetchMode("employees", FetchMode.JOIN);
            criteria.setFetchMode("infields", FetchMode.JOIN);
            criteria.setFetchMode("garages", FetchMode.JOIN);
        }
        return criteria.list().size() > 0 ? (List<Society>) criteria.list() : null;
    }

    @Override
    public Long addSociety(Society society) {
        Long societyId = -1l;

        try {
            societyId = (Long) getCurrentSession().save(society);
            getCurrentSession().flush();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return societyId;
    }

    @Override
    public Boolean updateSociety(Society society) {
        try {
            getCurrentSession().getTransaction().begin();
            getCurrentSession().update(society);
            getCurrentSession().getTransaction().commit();
            getCurrentSession().flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteSociety(Society society) {
        try {
            getCurrentSession().getTransaction().begin();
            getCurrentSession().delete(society);
            getCurrentSession().getTransaction().commit();
            getCurrentSession().flush();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
