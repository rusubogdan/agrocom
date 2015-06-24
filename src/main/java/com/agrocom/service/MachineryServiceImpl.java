package com.agrocom.service;

import com.agrocom.dao.MachineryDAO;
import com.agrocom.model.Machinery;
import com.agrocom.model.Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MachineryServiceImpl implements MachineryService {

    @Autowired
    private MachineryDAO machineryDAO;

    @Override
    public List<Machinery> getMachineriesBySociety(Society society) {
        return machineryDAO.getMachineriesBySociety(society);
    }

    @Override
    public Machinery getMachinery(Long machineryId) {
        return machineryDAO.getMachinery(machineryId);
    }
}
