package com.agrocom.service;

import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.model.UserSociety;

import java.util.List;

public interface UserSocietyService {

    UserSociety getUserSociety(Long id);

    UserSociety getUserSocietyByUserAndSociety(User user, Society society);

    List<UserSociety> getUserSocietyByUser(User user);

    Long addUserSociety (UserSociety userSociety);

    Boolean updateUserSociety(UserSociety userSociety);
}