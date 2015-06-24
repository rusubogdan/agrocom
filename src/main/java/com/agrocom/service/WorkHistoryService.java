package com.agrocom.service;

import com.agrocom.model.Society;
import com.agrocom.model.User;
import com.agrocom.model.WorkHistory;

import java.util.List;

public interface WorkHistoryService {

    WorkHistory getWorkHistory(Long id, Boolean fullData);

    List<WorkHistory> getWorkHistoryBySociety(Society society);

    List<WorkHistory> getWorkHistoryByUser(User user);

    List<WorkHistory> getWorkHistoryByUserAndSociety(User user, Society society);

    Long addWorkHistory(WorkHistory workHistory);

    Boolean updateWorkHistory(WorkHistory workHistory);

    List<WorkHistory> getWorkHistoryByListOfUsers(long[] ids);
}
