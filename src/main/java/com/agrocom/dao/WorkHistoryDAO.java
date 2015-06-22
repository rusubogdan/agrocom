package com.agrocom.dao;

import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.model.WorkHistory;

import java.util.List;

public interface WorkHistoryDAO {

    WorkHistory getWorkHistory(Long id, Boolean fullData);

    List<WorkHistory> getWorkHistoryBySociety(Society society);

    List<WorkHistory> getWorkHistoryByUser(User user);

    Long addWorkHistory(WorkHistory workHistory);

    Boolean updateWorkHistory(WorkHistory workHistory);
}
