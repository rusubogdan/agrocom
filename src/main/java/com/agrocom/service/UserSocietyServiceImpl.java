package com.agrocom.service;

import com.agrocom.dao.UserSocietyDAO;
import com.agrocom.model.Role;
import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.model.UserSociety;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserSocietyServiceImpl implements UserSocietyService {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(UserSocietyServiceImpl.class);

    @Autowired
    private UserSocietyDAO userSocietyDAO;

    @Override
    public UserSociety getUserSociety(Long id) {
        return userSocietyDAO.getUserSociety(id);
    }

    @Override
    public UserSociety getUserSocietyByUserAndSociety(User user, Society society) {
        return userSocietyDAO.getUserSocietyByUserAndSociety(user, society);
    }

    @Override
    public List<UserSociety> getUserSocietyByUser(User user) {
        return userSocietyDAO.getUserSocietyByUser(user);
    }

    @Override
    public List<UserSociety> getUserSocietyBySociety(Society society, Boolean isTenant, Role role) {
        return userSocietyDAO.getUserSocietyBySociety(society, isTenant, role);
    }

    @Override
    public Long addUserSociety(UserSociety userSociety) {
        return userSocietyDAO.addUserSociety(userSociety);
    }

    @Override
    public Boolean updateUserSociety(UserSociety userSociety) {
        return userSocietyDAO.updateUserSociety(userSociety);
    }
}
