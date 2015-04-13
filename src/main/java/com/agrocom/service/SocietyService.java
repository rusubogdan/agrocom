package com.agrocom.service;

import com.agrocom.model.Society;

public interface SocietyService {
    Society getSociety(Long societyId);

    Society getSocietyByName(String societyName);

    Long addSociety(Society society);

    Boolean updateSociety(Society society);

    Boolean deleteSociety(Society society);

}
