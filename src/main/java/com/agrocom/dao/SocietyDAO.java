package com.agrocom.dao;

import com.agrocom.model.Society;
import com.agrocom.model.User;

import java.util.List;

public interface SocietyDAO {

    Society getSociety(Long societyId, Boolean fullData);

    Society getSocietyByName(String societyName, Boolean fullData);

    List<Society> getSocietiesByUser(User user, Boolean fullData);

    Long addSociety(Society society);

    Boolean updateSociety(Society society);

    Boolean deleteSociety(Society society);
}
