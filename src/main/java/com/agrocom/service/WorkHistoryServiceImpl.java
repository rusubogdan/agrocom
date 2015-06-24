package com.agrocom.service;

import com.agrocom.dao.WorkHistoryDAO;
import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.model.WorkHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkHistoryServiceImpl implements WorkHistoryService {

    @Autowired
    private WorkHistoryDAO workHistoryDAO;

    @Override
    public WorkHistory getWorkHistory(Long id, Boolean fullData) {
        return workHistoryDAO.getWorkHistory(id, fullData);
    }

    @Override
    public List<WorkHistory> getWorkHistoryBySociety(Society society) {
        return workHistoryDAO.getWorkHistoryBySociety(society);
    }

    @Override
    public List<WorkHistory> getWorkHistoryByUser(User user) {
        return workHistoryDAO.getWorkHistoryByUser(user);
    }

    @Override
    public List<WorkHistory> getWorkHistoryByUserAndSociety(User user, Society society) {
        return workHistoryDAO.getWorkHistoryByUserAndSociety(user, society);
    }

    @Override
    public List<WorkHistory> getWorkHistoryByListOfUsers(long[] ids) {
        return workHistoryDAO.getWorkHistoryByListOfUsers(ids);
    }

    @Override
    public Long addWorkHistory(WorkHistory workHistory) {
        return workHistoryDAO.addWorkHistory(workHistory);
    }

    @Override
    public Boolean updateWorkHistory(WorkHistory workHistory) {
        return workHistoryDAO.updateWorkHistory(workHistory);
    }
}
