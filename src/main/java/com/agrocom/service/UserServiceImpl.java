package com.agrocom.service;

import com.agrocom.dao.UserDAO;
import com.agrocom.helpers.AppUtil;
import com.agrocom.helpers.Constants;
import com.agrocom.helpers.SignUpForm;
import com.agrocom.model.Role;
import com.agrocom.model.User;

import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String signUpConfirmUrl = "http://localhost:8080/confirm/";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MailService mailService;

    @Autowired
    RoleService roleService;

    public User getUser (Long userId) {
        return userDAO.getUser(userId);
    }

    public User getUserByEmail (String email) {
        return userDAO.getUserByEmail(email);
    }

    public User getUserByToken (String token) {
        return userDAO.getUserByToken(token);
    }

    public Integer addUser (User user) {
        user.setToken(generateRandomToken());
        // todo set encrypted password
//        user.setPassword();
//        every new created user will have the role user
        // todo think this part

        Role role = roleService.getRole(Role.ROLE_USER);
        user.setRole(role);

        return userDAO.addUser(user);
    }

    public Boolean updateUser (User user) {
        return userDAO.updateUser(user);
    }

    public Boolean deleteUser (User user) {
        return userDAO.deleteUser(user);
    }

    public User fromSignUpForm (SignUpForm signUpForm, Boolean thing) {
        User user = new User();
        user.setEmail(signUpForm.getEmail());
        // todo encoded password
        user.setPassword(signUpForm.getPassword());

        return user;
    }

    public Boolean sendSignUpEmail (User user) {
        String toEmail = user.getEmail();
        logger.info("Sending signUp email for " + toEmail);
        String subject = "Agrocom account verification";
        String htmlContent = "You are receiving this message because you tried to register to Agrocom " +
                "using this email address. " +
                "<br/>To confirm please click <a href=\"" + signUpConfirmUrl + user.getToken() + "\">here</a>.";
        return mailService.sendMessage(toEmail, subject, htmlContent);
    }

    private String generateRandomToken() {
        String token = AppUtil.generateRandomString(Constants.tokenValidChars, Constants.tokenLength);
        while (this.getUserByToken(token) != null) {
            token = AppUtil.generateRandomString(Constants.tokenValidChars, Constants.tokenLength);
        }
        return token;
    }
}
