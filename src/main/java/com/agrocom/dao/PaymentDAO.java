package com.agrocom.dao;

import com.agrocom.model.Payment;
import com.agrocom.model.Society;

import java.util.List;

public interface PaymentDAO {

    List<Payment> getPaymentBySociety(Society society);

}
