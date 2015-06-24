package com.agrocom.dao;

import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.model.WorkHistory;
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
public class WorkHistoryDAOImpl implements WorkHistoryDAO {

    Logger logger = LoggerFactory.getLogger(WorkHistoryDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public WorkHistory getWorkHistory(Long id, Boolean fullData) {
        Criteria criteria = getCurrentSession().createCriteria(WorkHistory.class);
        criteria.add(Restrictions.eq("workHistoryId", id));
        if (fullData) {
            criteria.setFetchMode("worker", FetchMode.JOIN);
            criteria.setFetchMode("infield", FetchMode.JOIN);
            criteria.setFetchMode("machinery", FetchMode.JOIN);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        }

        return criteria.list().size() > 0 ? (WorkHistory) criteria.list().get(0) : null;
    }

    @Override
    public List<WorkHistory> getWorkHistoryBySociety(Society society) {
        Criteria criteria = getCurrentSession().createCriteria(WorkHistory.class);
        criteria.add(Restrictions.eq("society", society));
        criteria.setFetchMode("worker", FetchMode.JOIN);
        criteria.setFetchMode("infield", FetchMode.JOIN);
        criteria.setFetchMode("machinery", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }

    @Override
    public List<WorkHistory> getWorkHistoryByUser(User user) {
        Criteria criteria = getCurrentSession().createCriteria(WorkHistory.class);
        criteria.add(Restrictions.eq("worker", user));
        criteria.setFetchMode("worker", FetchMode.JOIN);
        criteria.setFetchMode("infield", FetchMode.JOIN);
        criteria.setFetchMode("machinery", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }

    @Override
    public List<WorkHistory> getWorkHistoryByUserAndSociety(User user, Society society) {
        Criteria criteria = getCurrentSession().createCriteria(WorkHistory.class);
        criteria.add(
                Restrictions.and(
                        Restrictions.eq("worker", user),
                        Restrictions.eq("society", society)
                ));
        criteria.setFetchMode("worker", FetchMode.JOIN);
        criteria.setFetchMode("society", FetchMode.JOIN);
        criteria.setFetchMode("infield", FetchMode.JOIN);
        criteria.setFetchMode("machinery", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }

    @Override
    public List<WorkHistory> getWorkHistoryByListOfUsers(long[] ids) {
//        Criteria criteria = getCurrentSession().createCriteria(WorkHistory.class);
//        criteria.add(Restrictions.in("worker", ids))
        return  null;
    }

    @Override
    public Long addWorkHistory(WorkHistory workHistory) {
        Long savedWHId = -1l;

        try {
            savedWHId = (Long) getCurrentSession().save(workHistory);
            getCurrentSession().flush();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return savedWHId;
    }

    @Override
    public Boolean updateWorkHistory(WorkHistory workHistory) {
        try {
            getCurrentSession().getTransaction().begin();
            getCurrentSession().update(workHistory);
            getCurrentSession().getTransaction().commit();
            getCurrentSession().flush();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
