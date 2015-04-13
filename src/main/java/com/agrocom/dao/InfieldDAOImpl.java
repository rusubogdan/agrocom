package com.agrocom.dao;

import com.agrocom.model.Infield;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InfieldDAOImpl implements InfieldDAO {

    Logger logger = LoggerFactory.getLogger(InfieldDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Infield getInfield(Long infieldId) {
        return (Infield) getCurrentSession().load(Infield.class, infieldId);
    }

    @Override
    public Infield getInfieldByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(Infield.class);
        criteria.add(Restrictions.eq("locationCode", code));

        return criteria.list() != null ? (Infield) criteria.list().get(0) : null;
    }

    @Override
    public Long addInfield(Infield infield) {
        Long infieldId = -1l;

        try {
            getCurrentSession().save(infield);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return infieldId;
    }

    @Override
    public Boolean updateInfield(Infield infield) {
        try {
            getCurrentSession().update(infield);
            getCurrentSession().getTransaction().commit();
            getCurrentSession().flush();

            return true;
        } catch (Exception e) {
            logger.warn(e.getMessage());

            return false;
        }
    }

    @Override
    public Boolean deleteInfield(Infield infield) {
        try {
            getCurrentSession().delete(infield);
            getCurrentSession().getTransaction().commit();
            getCurrentSession().flush();

            return true;
        } catch (Exception e) {
            logger.warn(e.getMessage());

            return false;
        }
    }
}