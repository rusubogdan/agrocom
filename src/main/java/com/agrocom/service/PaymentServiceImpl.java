package com.agrocom.service;

import com.agrocom.dao.PaymentDAO;
import com.agrocom.model.Payment;
import com.agrocom.model.Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDAO paymentDAO;

    @Override
    public List<Payment> getPaymentBySociety(Society society) {
        return paymentDAO.getPaymentBySociety(society);
    }
}
