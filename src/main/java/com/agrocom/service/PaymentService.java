package com.agrocom.service;

import com.agrocom.model.Payment;
import com.agrocom.model.Society;

import java.util.List;

public interface PaymentService {
    List<Payment> getPaymentBySociety(Society society);
}
