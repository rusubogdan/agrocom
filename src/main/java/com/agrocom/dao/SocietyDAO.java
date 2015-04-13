package com.agrocom.dao;

import com.agrocom.model.Society;

public interface SocietyDAO {

    Society getSociety(Long societyId);

    Society getSocietyByName(String societyName);

    Long addSociety(Society society);

    Boolean updateSociety(Society society);

    Boolean deleteSociety(Society society);
}
