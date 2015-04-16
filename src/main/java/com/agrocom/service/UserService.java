package com.agrocom.service;

import com.agrocom.model.User;

import java.util.List;

public interface UserService {

    User getUser(Long userId);

    User getUserByEmail(String email);

    User getUserByToken(String token);

    User getUserByFirstAndLastName(String firstName, String lastName);

    List searchUserByFirstName(String firstName);

    List searchUserByLastName(String lastName);

    Integer addUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(User user);

    User createUserWithoutSaving(String firstName, String lastName, String email, String password);

    Boolean sendSignUpEmail(User user);
}
