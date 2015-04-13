package com.agrocom.dao;

import com.agrocom.model.User;

public interface UserDAO {

    User getUser(Long userId);

    User getUserByEmail(String email);

    User getUserByToken(String token);

    Integer addUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(User user);
}
