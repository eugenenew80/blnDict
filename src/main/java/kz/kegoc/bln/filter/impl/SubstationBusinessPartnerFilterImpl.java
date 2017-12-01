package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.SubstationBusinessPartner;
import kz.kegoc.bln.filter.Filter;

import javax.ejb.Stateless;

@Stateless
public class SubstationBusinessPartnerFilterImpl implements Filter<SubstationBusinessPartner> {
    public SubstationBusinessPartner filter(SubstationBusinessPartner entity) {
        return entity;
    }
}
