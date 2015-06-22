package com.agrocom.service;

import com.agrocom.model.Infield;

public interface InfieldService {

    Infield getInfield(Long infieldId, Boolean fullData);

    Infield getInfieldByCode(String code);

    Long addInfield(Infield infield);

    Boolean updateInfield(Infield infield);

    Boolean deleteInfield(Infield infield);
}
