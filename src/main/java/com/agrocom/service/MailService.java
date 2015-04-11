package com.agrocom.service;

public interface MailService {

    Boolean sendMessage(String toEmail, String subject, String htmlContent);
}
