package com.agrocom.dao;


import com.agrocom.model.Machinery;
import com.agrocom.model.Society;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.crypto.Mac;
import java.util.List;

@Repository
public class MachineryDAOImpl implements MachineryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Machinery> getMachineriesBySociety(Society society) {
        Criteria criteria = getCurrentSession().createCriteria(Machinery.class);
//        criteria.add(Restrictions.eq("society", society));
//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Machinery getMachinery(Long machineryId) {
        Criteria criteria = getCurrentSession().createCriteria(Machinery.class);
        criteria.add(Restrictions.eq("machineryId", machineryId));

        return criteria.list().size() > 0 ? (Machinery) criteria.list().get(0) : null;
    }
}
