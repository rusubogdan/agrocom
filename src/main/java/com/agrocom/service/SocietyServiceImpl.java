package com.agrocom.service;

import com.agrocom.dao.SocietyDAO;
import com.agrocom.model.Society;

import com.agrocom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SocietyServiceImpl implements SocietyService {

    @Autowired
    private SocietyDAO societyDAO;

    @Override
    public Society getSociety(Long societyId, Boolean fullData) {
        return societyDAO.getSociety(societyId, fullData);
    }

    @Override
    public Society getSocietyByName(String societyName, Boolean fullData) {
        return societyDAO.getSocietyByName(societyName, fullData);
    }

    @Override
    public List<Society> getSocietiesByUser(User user, Boolean fullData) {
        return societyDAO.getSocietiesByUser(user, fullData);
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
