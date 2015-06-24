package com.agrocom.dao;

import com.agrocom.model.Machinery;
import com.agrocom.model.Society;

import java.util.List;

public interface MachineryDAO {

    List<Machinery> getMachineriesBySociety(Society society);

    Machinery getMachinery(Long machineryId);
}
