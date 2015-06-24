package com.agrocom.dao;

import com.agrocom.model.Role;
import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.model.UserSociety;

import java.util.List;

public interface UserSocietyDAO {

    UserSociety getUserSociety(Long id);

    UserSociety getUserSocietyByUserAndSociety(User user, Society society);

    List<UserSociety> getUserSocietyByUser(User user);

    List<UserSociety> getUserSocietyBySociety(Society society, Boolean isTenant, Role role);

    List<Society> getSocietiesByUser(User user);

    Long addUserSociety (UserSociety userSociety);

    Boolean updateUserSociety(UserSociety userSociety);
}
