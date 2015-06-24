package com.agrocom.service;

import com.agrocom.model.Machinery;
import com.agrocom.model.Society;

import java.util.List;

public interface MachineryService {

    List<Machinery> getMachineriesBySociety(Society society);

    Machinery getMachinery(Long machineryId);
}
