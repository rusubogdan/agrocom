package com.agrocom.dao;

import com.agrocom.model.Infield;

public interface InfieldDAO {

    Infield getInfield(Long infieldId, Boolean fullData);

    Infield getInfieldByCode(String code);

    Long addInfield(Infield infield);

    Boolean updateInfield(Infield infield);

    Boolean deleteInfield(Infield infield);
}
