package com.agrocom.dao;

import com.agrocom.model.Society;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SocietyDAOImpl implements SocietyDAO {

    Logger logger = LoggerFactory.getLogger(SocietyDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Society getSociety(Long societyId) {
        return (Society) getCurrentSession().load(Society.class, societyId);
    }

    @Override
    public Society getSocietyByName(String societyName) {
        Criteria criteria = getCurrentSession().createCriteria(Society.class);
        criteria.add(Restrictions.eq("societyName", societyName));
        return criteria.list() != null ? (Society) criteria.list().get(0) : null;
    }

    @Override
    public Long addSociety(Society society) {
        Long societyId = -1l;

        try {
            societyId = (Long) getCurrentSession().save(society);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return societyId;
    }

    @Override
    public Boolean updateSociety(Society society) {
        try {
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
            getCurrentSession().delete(society);
            getCurrentSession().getTransaction().commit();
            getCurrentSession().flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
