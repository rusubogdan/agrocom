package com.agrocom.helpers;

import com.agrocom.controller.HomeController;
import com.agrocom.model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MinifyUtil {

    public static synchronized User minifyUser(User user) {
        user.setRole(null);
        user.setIsConfirmed(null);
        user.setDeletedDate(null);
        user.setEmployingSociety(null);
        user.setOwnedSocieties(null);
        user.setPassword(null);
        user.setInfields(null);
        user.setRegisterDate(null);
        user.setToken(null);

        return user;
    }

    public static synchronized Infield minifyInfield(Infield infield) {
        infield.setSociety(null);
        infield.setTenant(null);

        return infield;
    }

    public static synchronized Machinery minifyMachinery(Machinery machinery) {
        return machinery;
    }

    public static synchronized List<User> minifyUsers(List<User> users) {
        users.forEach(com.agrocom.helpers.MinifyUtil::minifyUser);
        return users;
    }

    public static synchronized List<Infield> minifyInfields(List<Infield> infields) {
        infields.forEach(com.agrocom.helpers.MinifyUtil::minifyInfield);
        return infields;
    }

    public static synchronized List<Machinery> minifyMachineries(List<Machinery> machineries) {
        machineries.forEach(com.agrocom.helpers.MinifyUtil::minifyMachinery);
        return machineries;
    }

    public static synchronized List<HomeController.WorkHistoryMin> getMinifiedActivities(List<WorkHistory> activities) {



        return null;
    }

}
