package com.agrocom.dao;

import com.agrocom.model.Society;
import com.agrocom.model.User;

import java.util.List;

public interface UserDAO {

    User getUser(Long userId, Boolean fullData);

    User getUserByEmail(String email);

    User getUserByToken(String token);

    User getUserByFirstAndLastName(String firstName, String lastName);

    List searchUserByFirstName(String firstName);

    List searchUserByLastName(String lastName);

    

    Long addUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(User user);

    User getUserByPin(String pin);

    List<User> getLandlordsBySociety(Society society);
}
