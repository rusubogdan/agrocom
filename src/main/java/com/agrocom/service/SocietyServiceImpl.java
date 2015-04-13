package com.agrocom.service;

import com.agrocom.dao.SocietyDAO;
import com.agrocom.model.Society;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocietyServiceImpl implements SocietyService {

    @Autowired
    private SocietyDAO societyDAO;

    @Override
    public Society getSociety(Long societyId) {
        return societyDAO.getSociety(societyId);
    }

    @Override
    public Society getSocietyByName(String societyName) {
        return societyDAO.getSocietyByName(societyName);
    }

    @Override
    public Long addSociety(Society society) {
        return societyDAO.addSociety(society);
    }

    @Override
    public Boolean updateSociety(Society society) {
        return societyDAO.updateSociety(society);
    }

    @Override
    public Boolean deleteSociety(Society society) {
        return societyDAO.deleteSociety(society);
    }
}
