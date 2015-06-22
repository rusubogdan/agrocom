package com.agrocom.service;

import com.agrocom.dao.InfieldDAO;
import com.agrocom.model.Infield;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InfieldServiceImpl implements InfieldService {

    @Autowired
    private InfieldDAO infieldDAO;

    @Override
    public Infield getInfield(Long infieldId, Boolean fullData) {
        return infieldDAO.getInfield(infieldId, fullData);
    }

    @Override
    public Infield getInfieldByCode(String code) {
        return infieldDAO.getInfieldByCode(code);
    }

    @Override
    public Long addInfield(Infield infield) {
        return infieldDAO.addInfield(infield);
    }

    @Override
    public Boolean updateInfield(Infield infield) {
        return infieldDAO.updateInfield(infield);
    }

    @Override
    public Boolean deleteInfield(Infield infield) {
        return infieldDAO.deleteInfield(infield);
    }
}
