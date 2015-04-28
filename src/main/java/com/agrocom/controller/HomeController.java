package com.agrocom.controller;

import com.agrocom.service.MailService;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
@PropertySource("classpath:application.properties")
public class HomeController {

    private static final String sendGridUsername = "mail.sendGrid.username";
    private static final String sendGridPassword = "mail.sendGrid.password";

    @Resource
    private Environment env;

    @Autowired
    MailService mailService;


}
