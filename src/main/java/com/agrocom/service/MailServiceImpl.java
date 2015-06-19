package com.agrocom.service;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class MailServiceImpl implements MailService {

    @Value("${mail.sendGrid.apiKey}")
    private String sendGridApiKey;

    private static final String sendGridFrom = "no-reply@gmail.com";

    private Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Override
    public Boolean sendMessage(String toEmail, String subject, String htmlContent) {
        SendGrid sendGridClient = new SendGrid(sendGridApiKey);
        SendGrid.Email message = new SendGrid.Email();
        message.addTo(toEmail);
        message.setFrom(sendGridFrom);
        message.setSubject(subject);
        message.setHtml(htmlContent);

        try {
            SendGrid.Response response = sendGridClient.send(message);
            boolean success = response.getStatus();

            if (success) {
                logger.info("Email sent successfully.");
            } else {
                logger.warn("Email was not sent. Response from SendGrid: code=" + response.getCode() + ", " +
                        "message=" + response.getMessage());
            }

            return success;
        } catch (SendGridException e) {
            logger.warn("Error occurred while trying to send email. ", e);
            return false;
        }
    }
}
