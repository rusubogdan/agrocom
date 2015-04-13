package com.agrocom.service;

import com.agrocom.helpers.SignUpForm;
import com.agrocom.model.User;

public interface UserService {

    User getUser(Long userId);

    User getUserByEmail(String email);

    User getUserByToken(String token);

    Integer addUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(User user);

    // additionally to UserDAO
    User fromSignUpForm(SignUpForm signUpForm, Boolean b);

    Boolean sendSignUpEmail(User user);
}
