package com.agrocom.service;

import com.agrocom.dao.UserDAO;
import com.agrocom.helpers.AppUtil;
import com.agrocom.helpers.Constants;
import com.agrocom.model.Role;
import com.agrocom.model.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@PropertySource("classpath:application.properties")
public class UserServiceImpl implements UserService {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MailService mailService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value(value = "${account.confirm.url}")
    private String signUpConfirmUrl;

    public User getUser (Long userId) {
        return userDAO.getUser(userId);
    }

    public User getUserByEmail (String email) {
        return userDAO.getUserByEmail(email);
    }

    public User getUserByToken (String token) {
        return userDAO.getUserByToken(token);
    }

    public User getUserByPin(String pin) {
        return userDAO.getUserByPin(pin);
    }

    public User getUserByFirstAndLastName (String firstName, String lastName) {
        return userDAO.getUserByFirstAndLastName(firstName, lastName);
    }

    public List<User> searchUserByFirstName (String firstName) {
        return userDAO.searchUserByFirstName(firstName);
    }

    public List<User> searchUserByLastName (String lastName) {
        return userDAO.searchUserByLastName(lastName);
    }

    public Long addUser (User user) {
//        user.setToken(generateRandomToken());
        // todo set encrypted password
//        user.setPassword();
//        every new created user will have the role user
        // todo think this part

//        Role role = roleService.getRole(Role.ROLE_USER);
//        user.setRole(role);

        return userDAO.addUser(user);
    }

    public Boolean updateUser (User user) {
        return userDAO.updateUser(user);
    }

    public Boolean deleteUser (User user) {
        return userDAO.deleteUser(user);
    }

    public User createUserWithoutSaving (String firstName, String lastName, String email, String pin, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPIN(pin);

        user.setToken(generateRandomToken());

        // todo confirm account via email confirmation
        user.setIsConfirmed(false);

        // todo date, etc

        // todo salted password
        user.setPassword(passwordEncoder.encode(password));

        Role userRole = roleService.getRole(Role.ROLE_USER);
        user.setRole(userRole);

        return user;
    }

    public Boolean sendSignUpEmail (User user) {
        String toEmail = user.getEmail();
        logger.info("Sending signUp email for " + toEmail);
        String subject = "Agrocom account verification";
        String htmlContent = "You are receiving this message because you tried to register to Agrocom " +
                "using this email address. " +
                "<br/>To confirm please click <a href=\"http://" + signUpConfirmUrl + user.getToken() + "\">here</a>.";
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
