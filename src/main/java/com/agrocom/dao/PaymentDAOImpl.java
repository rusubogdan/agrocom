package com.agrocom.dao;

import com.agrocom.model.Payment;
import com.agrocom.model.Society;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Payment> getPaymentBySociety(Society society) {
        Criteria criteria = getCurrentSession().createCriteria(Payment.class);
        criteria.add(Restrictions.eq("society", society));

        criteria.setFetchMode("society", FetchMode.JOIN);
        criteria.setFetchMode("tenant", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }
}
